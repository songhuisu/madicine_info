package com.example.android_medicine_info;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class GetXmlParse extends AsyncTask<String, Void, Document> {

    Button send;
    TextView result;

    @Override
    protected Document doInBackground(String... urls) {
        URL url;
        Document doc = null;
        
        try {
            url = new URL("http://apis.data.go.kr/1470000/MdcinGrnIdntfcInfoService/getMdcinGrnIdntfcInfoList?ServiceKey=mJSw%2BbK1%2FWlQLGv2KJ9Gmfnp648MfO6DIlS06eJPHpy56zlq8R6AfiTGsL35iuFduMZ3S4LBL9y%2FJRzonyaO4w%3D%3D&numOfRows=3&pageNo=1&edi_code=642003260");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();

        } catch (Exception e) {

        }
        return doc;
    }


    @Override
    protected void onPostExecute(Document doc) {

        String s = "";
        NodeList nodeList = doc.getElementsByTagName("item");

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);
            Element fstElmnt = (Element) node;

            NodeList item_name = fstElmnt.getElementsByTagName("item_name");
            s += "item_name = " + item_name.item(0).getChildNodes().item(0).getNodeValue() + "\n";

            NodeList entp_name = fstElmnt.getElementsByTagName("entp_name");
            s += "entp_name = " + entp_name.item(0).getChildNodes().item(0).getNodeValue() + "\n";

 /*           NodeList img_regist_ts = fstElmnt.getElementsByTagName("img_regist_ts");
            s += "img_regist_ts = " + img_regist_ts.item(0).getChildNodes().item(0).getNodeValue() + "\n";*/

            NodeList pageNo = fstElmnt.getElementsByTagName("pageNo");
            s += "pageNo = " + pageNo.item(0).getChildNodes().item(0).getNodeValue() + "\n";

            NodeList numOfRows = fstElmnt.getElementsByTagName("numOfRows");
            s += "numOfRows = " + numOfRows.item(0).getChildNodes().item(0).getNodeValue() + "\n";

            NodeList edi_code = fstElmnt.getElementsByTagName("edi_code");
            s += "edi_code = " + edi_code.item(0).getChildNodes().item(0).getNodeValue() + "\n";

        }

        result.setText(s);

        super.onPostExecute(doc);
    }
}