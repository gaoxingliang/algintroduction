package Vote;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 14-3-25.
 */
public class Vote
{
    public static void main(String []args) throws Exception{


        HttpClient client = new HttpClient();
        client.getHostConfiguration().setProxy("127.0.0.1",8580);

        UsernamePasswordCredentials creds = new UsernamePasswordCredentials("","");
        client.getState().setProxyCredentials(AuthScope.ANY, creds);
        GetMethod get = new GetMethod("http://xgb.scu.edu.cn:8080/vote/");
        client.executeMethod(get);


        StringBuilder sb = new StringBuilder();
        sb.append("http://xgb.scu.edu.cn:8080/vote/VoteServlet?checkbox=8&");
        for(int i=0;i<14;i++)
        {
            sb.append("checkbox=").append(8).append("&");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
        PostMethod post = new PostMethod(sb.toString());
        client.executeMethod(post);
        String postS = post.getResponseBodyAsString();
        post.releaseConnection();
        if(postS.contains("亲，您已经投过3次了哈"))
        {
            System.out.println("false");
        }else
        {
            System.out.println("suc");
        }
    }



}
 class O
{
    public String p,box;
    public int count;
}