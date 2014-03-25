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

        String str = get.getResponseBodyAsString();
        get.releaseConnection();
        Document doc = Jsoup.parse(str);
        Elements trs = doc.getElementsByTag("form").get(0).child(0).child(0).children();
        Elements tds =null;
        Element e,e2;
        String t;
        List<O> list = new ArrayList<O>();
        for (int i=0;i<trs.size();i++)
        {
            e = trs.get(i);
            tds =e.children();
            if(tds.get(0).hasAttr("align"))
            {
                for (Element e3:tds)
                {
                    O o = new O();
                    o.p = e3.getElementsByTag("a").html();
                    o.box = e3.getElementsByTag("input").val();
                    t =e3.getElementsByTag("p").html();
                    o.count = Integer.valueOf(t.substring(t.length()-4));
                    list.add(o);
                }
            }
        }

        Collections.sort(list,new Comparator<O>()
        {
            @Override
            public int compare(O o1, O o2)
            {
                if (o1.p.contains("东园13舍107"))
                {
                    return -1;
                }
                return o1.count>o2.count?1:-1;
            }
        });

        StringBuilder sb = new StringBuilder();
        sb.append("http://xgb.scu.edu.cn:8080/vote/VoteServlet?checkbox=8&");
        for(int i=0;i<9;i++)
        {
            sb.append("checkbox=").append(list.get(i).box).append("&");
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