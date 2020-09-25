package kr.co.ex.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

public class Utils {

  // private static final Logger logger = LoggerFactory.getLogger(Utils.class);

  public static JSONObject jsonParse(String jsonStr) throws IOException, ParseException {

    InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(jsonStr.getBytes(Charset.forName("UTF-8"))), Charset.forName("UTF-8"));
    JSONParser jsonParser = new JSONParser();
    JSONObject obj;
    obj = (JSONObject) jsonParser.parse(isr);
    return obj;
  }

}