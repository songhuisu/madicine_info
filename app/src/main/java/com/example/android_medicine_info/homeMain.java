package com.example.android_medicine_info;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class homeMain extends Activity{

    Toolbar toolbar;
    ImageView imageView = null;
    ImageView imageView2 = null;
    Button btn_camera = null;
    Button btn_guide = null;
    View diView;
    TextView textView2 = null;
    Button btnText = null;
    Button btnVision = null;
    EditText edit;
    TextView result;
    XmlPullParser xpp;
    String data;
    Button send;
    final static int TAKE_PICTURE = 1;

    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;

   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);

        btnText= (Button)findViewById(R.id.btnText);
        btnVision = (Button)findViewById(R.id.btnVision);

       btnText.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(), OcrActivity.class);

               startActivity(intent);
           }
       });
       btnVision.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(), Vision_info.class);

               startActivity(intent);
           }
       });

       edit= (EditText)findViewById(R.id.txtEdit);
       result= (TextView)findViewById(R.id.result);
       send = (Button)findViewById(R.id.send);

       send.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               switch (v.getId()){
                   case R.id.send:
                       new Thread(new Runnable() {
                           @Override
                           public void run() {
                               data=getXmlData();


                               runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       result.setText(data);

                                       // item_name.setText();
                                   }
                               });
                           }
                       }).start();
                       break;
               }
           }
       });
    }

    String getXmlData(){
        StringBuffer buffer=new StringBuffer();
        String str= edit.getText().toString();//EditText에 작성된 Text얻어오기

        String queryUrl="http://apis.data.go.kr/1470000/MdcinGrnIdntfcInfoService/getMdcinGrnIdntfcInfoList?ServiceKey=mJSw%2BbK1%2FWlQLGv2KJ9Gmfnp648MfO6DIlS06eJPHpy56zlq8R6AfiTGsL35iuFduMZ3S4LBL9y%2FJRzonyaO4w%3D%3D&item_name=타이레놀";

        try{
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType= xpp.getEventType();
            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//테그 이름 얻어오기

                        if(tag.equals("item")); // 첫번째 검색결과
                        else if(tag.equals("ITEM_SEQ")){
                            buffer.append("품목일련번호 : ");
                            xpp.next();
                            buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가

                            //item_name.setText(tag.);

                        }
                        else if(tag.equals("ITEM_NAME")){
                            buffer.append("품목명 : ");
                            xpp.next();
                            buffer.append(xpp.getText());//category 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n");//줄바꿈 문자 추가
                        }
                        else if(tag.equals("ENTP_SEQ")){
                            buffer.append("업체일련번호 :");
                            xpp.next();
                            buffer.append(xpp.getText());//description 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n");//줄바꿈 문자 추가
                        }else if(tag.equals("ENTP_NAME")){
                            buffer.append("업체명 :");
                            xpp.next();
                            buffer.append(xpp.getText());//description 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n");//줄바꿈 문자 추가
                        }
                        else if(tag.equals("CHART")){
                            buffer.append("성상 :");
                            xpp.next();
                            buffer.append(xpp.getText());//telephone 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n");//줄바꿈 문자 추가
                        }
                        else if(tag.equals("ITEM_IMAGE")){
                            buffer.append("큰제품이미지 :");
                            xpp.next();
                            buffer.append(xpp.getText());//address 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n");//줄바꿈 문자 추가
                        }
                        else if(tag.equals("PRINT_FRONT")){
                            buffer.append("표시(앞) :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapx 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }
                        else if(tag.equals("PRINT_BACK")){
                            buffer.append("표시(뒤) :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("DRUG_SHAPE")){
                            buffer.append("의약품모양 :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("COLOR_CLASS1")){
                            buffer.append("색깔(앞) :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("COLOR_CLASS2")){
                            buffer.append("색깔(뒤) :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("LINE_FRONT")){
                            buffer.append("분할선(앞) :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("LINE_BACK")){
                            buffer.append("분할선(뒤) :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("LENG_LONG")){
                            buffer.append("크기(장축) :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("LENG_SHORT")){
                            buffer.append("크기(단축) :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("THICK")){
                            buffer.append("크기(두께) :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("IMG_REGIST_TS")){
                            buffer.append("약학정보원 이미지 생성일 :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("CLASS_NO")){
                            buffer.append("분류번호 :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("CLASS_NAME")){
                            buffer.append("분류명 :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("ETC_OTC_NAME")){
                            buffer.append("전문/일반 :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("ITEM_PERMIT_DATE")){
                            buffer.append("품목허가일자 :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("FORM_CODE_NAME")){
                            buffer.append("제형코드이름 :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("MARK_CODE_FRONT_ANAL")){
                            buffer.append("마크내용(앞) :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("MARK_CODE_BACK_ANAL")){
                            buffer.append("마크내용(뒤) :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("MARK_CODE_FRONT_IMG")){
                            buffer.append("마크이미지(앞) :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("MARK_CODE_BACK_IMG")){
                            buffer.append("마크이미지(뒤) :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("ITEM_ENG_NAME")){
                            buffer.append("제품영문명 :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("CHANGE_DATE")){
                            buffer.append("변경일자 :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("MARK_CODE_FRONT")){
                            buffer.append("마크코드(앞) :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("MARK_CODE_BACK")){
                            buffer.append("마크코드(뒤) :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }else if(tag.equals("EDI_CODE")){
                            buffer.append("보험코드 :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //테그 이름 얻어오기

                        if(tag.equals("item")) buffer.append("\n");// 첫번째 검색결과종료..줄바꿈
                        break;
                }
                eventType= xpp.next();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        buffer.append("파싱 끝\n");
        return buffer.toString();//StringBuffer 문자열 객체 반환

    }//getXmlData method....

}
