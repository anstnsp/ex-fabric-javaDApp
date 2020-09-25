package kr.co.ex.fabric;

import java.util.Collection;
import java.util.EnumSet;
import java.util.function.Consumer;

import org.hyperledger.fabric.gateway.GatewayRuntimeException;

import org.hyperledger.fabric.gateway.impl.GatewayUtils;

import org.hyperledger.fabric.gateway.impl.event.BlockEventSource;
import org.hyperledger.fabric.gateway.impl.event.BlockEventSourceFactory;
import org.hyperledger.fabric.gateway.impl.event.ListenerSession;
import org.hyperledger.fabric.gateway.impl.event.OrderedBlockEventSource;
import org.hyperledger.fabric.sdk.BlockEvent;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.Peer;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;

public class ReplayListenerSession implements ListenerSession {
  private final GatewayImpl gateway;
  private final Channel channel;
  private final BlockEventSource blockSource;

  public ReplayListenerSession(final NetworkImpl network, final Consumer<BlockEvent> listener, final long startBlock) {
      gateway = network.getGateway().newInstance();
      String channelName = network.getChannel().getName();
      channel = gateway.getNetwork(channelName).getChannel();

      // Remove old peers first to avoid receiving spurious events from them
      Collection<Peer> eventingPeers = channel.getPeers(EnumSet.of(Peer.PeerRole.EVENT_SOURCE));
      removeAllPeers();

      // Attach listener before replay peers to ensure no replay events are missed
      BlockEventSource channelBlockSource = BlockEventSourceFactory.getInstance().newBlockEventSource(channel);
      blockSource = new OrderedBlockEventSource(channelBlockSource, startBlock);
      blockSource.addBlockListener(listener);

      addReplayPeers(eventingPeers, startBlock);
  }

  private void removeAllPeers() {
      try {
          for (Peer peer : channel.getPeers()) {
              channel.removePeer(peer);
          }
      } catch (InvalidArgumentException e) {
          throw new GatewayRuntimeException("Failed to remove peers from channel", e);
      }
  }

  private void addReplayPeers(final Collection<Peer> eventingPeers, final long startBlock) {
      HFClient client = gateway.getClient();
      try {
          for (Peer originalPeer : eventingPeers) {
              Peer replayPeer = client.newPeer(originalPeer.getName(), originalPeer.getUrl(), originalPeer.getProperties());
              Channel.PeerOptions options = Channel.PeerOptions.createPeerOptions()
                      .addPeerRole(Peer.PeerRole.EVENT_SOURCE)
                      .startEvents(startBlock);
              channel.addPeer(replayPeer, options);
          }
      } catch (InvalidArgumentException e) {
          throw new GatewayRuntimeException("Failed to add peers for event replay", e);
      }
  }

  @Override
  public void close() {
      blockSource.close();
      gateway.close();
  }

  @Override
  public String toString() {
      return GatewayUtils.toString(this,
              "channel=" + channel,
              "blockSource=" + blockSource);
  }
}