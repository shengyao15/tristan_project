package s2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class Eastmoney2 {
	
	
	public static void main(String[] args) throws Exception {
		//testFilter();
		gen();
	}
	public static void gen() throws Exception {
		
		String name = "智能机器板块.txt";
		
		InputStreamReader isr = new InputStreamReader(new FileInputStream("stockCode/"+name), "UTF-8");
		BufferedReader br2 = new BufferedReader(isr);

		long begin = System.currentTimeMillis(); // 测试起始时间
		
		File fs2 = new File("stockInfo/"+name);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fs2), "UTF-8"));
		
		String temp2 = "";
		while ((temp2 = br2.readLine()) != null) {
			if("end".equals(temp2)){
				long end = System.currentTimeMillis(); // 测试结束时间
				System.out.println("操作所需时间：" + (end - begin)/1000 + "s"); // 打印使用时间
				System.exit(-1);
			}
			if(temp2.length()!=6){
				continue;
			}
			if(temp2.startsWith("60")){
				temp2 = "sh"+temp2;
			}else{
				temp2 = "sz"+temp2;
			}
			
			System.out.println(temp2);
			
			HttpPost httpost = new HttpPost("http://f10.eastmoney.com/f10_v2/OperationsRequired.aspx?code="+temp2);
			org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();

			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity = response.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
			String temp = "";
			int i = 0;
			
			String stockName = "";
			
			while ((temp = br.readLine()) != null) {
				i++;
				if(i==9){
					//System.out.println(temp.substring(6, 20).replace("操", "").trim());
					stockName = temp.substring(6, 20).replace("操", "").trim();
					if(stockName.length()==11){
						stockName = "  "+stockName;
					}
					bw.write(stockName);
				}
					
				if(temp.contains("<div class=\"summary\">")){
					//System.out.println(filterContent(temp));
					bw.write(filterContent(temp, stockName));
				}else if(temp.contains("<div class=\"report\">")){
					//System.out.println(filterContent(temp));
					bw.write(filterContent(temp, stockName));
				}else if(temp.contains("<strong>研报摘要")){
					//System.out.println();
					//System.out.println();
					bw.write("\r\n");
					bw.write("\r\n");
					bw.write(stockName + "   |   ");
				}
				
			}
			bw.write("\r\n");
			bw.write("\r\n");
			bw.flush();
			br.close();
			
		}
		
		bw.close();
		
	}
	private static String filterContent(String temp, String stockName) {
		java.util.regex.Pattern p_html; 
		java.util.regex.Matcher m_html; 
		String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式 
		p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
		m_html = p_html.matcher(temp); 
		temp = m_html.replaceAll(""); //过滤html标签 

		String stockName2 = stockName+"    |   ";
		
		temp = temp.replaceAll("要点", "\r\n" +stockName2+ "要点");
		
		temp = temp.replaceAll("\\[", "");
		temp = temp.replaceAll("\\]", "");
		temp = temp.replaceAll("查看全文", "\r\n" + stockName2);
		
		return temp;
		
	}
	
	public static void testFilter(){
		String s = "<div class=\"summary\"><p>要点一：<font>所属板块</font>　QFII重仓板块，基金重仓板块，深圳特区板块，机构重仓板块，公募增发板块，股权激励板块，深成40板块，广东板块，房地产板块，AB股板块，HS300C板块，指数权重板块，定向增发板块，融资融券板块，央视50C板块。</p><p>要点二：<font>经营范围</font>　兴办实业 (具体项目另行申报) ；国内商业；物资供销业 (不含专营、专控、专卖商品) ；进出口业务 (按深经发审证字第113 号外贸企业审定证书规定办理) ；房地产开发。控股子公司主营业务包括房地产开发、物业管理、投资咨询等。公司为国内规模最大的地产发展商，连续三年被国务院发展研究中心企业所、清华大学房地产研究所和中国指数研究院评为中国房地产百强开发企业第一名，并获得CCTV评选的中国最具价值上市公司称号。公司在全国房地产市场的战略布局已经初步形成。</p><p>要点三：<font>行业龙头</font>　2008中国房地产百强研究报告披露，综合考虑企业规模性，盈利性，成长性，偿债能力，运营效率和纳税六个方面的20个指标，万科企业股份有限公司位列房地产企业综合实力第一。2011年上半年公司实现销售面积565.5万平方米，销售金额656.5亿元，同比分别增长76.7%和78.6%。2011年上半年，公司销售金额占全国商品房销售额的比例为2.67%。截至2011年6月末，公司合并报表范围内共有1022万平方米已销售资源尚未结算，合同金额合计约1187亿元。</p><p>要点四：<font>高预收账款/存货/货币资金</font>　2011年中报披露，预收账款期末数1070.73亿元，年初数为744.05亿元。公司持有货币资金407.79亿元，期初为378.2亿元，存货期末为1713.66亿元(其中在建开发产品1113.63亿元，拟开发产品552.05亿元，已完工开发产品47.52亿元)，期初为1333.33亿元。</p><p>要点五：<font>项目资源</font>　截至2010年末，公司开发项目245个，在建项目万科权益建筑面积合计约1362万平方米，规划中项目万科权益建筑面积合计约3640万平方米。2011年现有项目预计开工面积1329万平方米，比2010年增加6%，预计2011年项目竣工面积729万平方米，比2010年增加65%。2011年1-6月，公司新增加开发项目22个，按万科权益计算的占地面积约222万平方米(对应规划建筑面积约423万平方米)，平均楼面地价约2823元/平方米。此外，期内公司还参与城市更新改造类项目4个。截至2011年6月末，按万科权益计算的规划建筑面积3585万平方米。</p><p>要点六：<font>加大二，三线城市业务</font>　公司已经加大了在中国二，三线城市房地产的开发，2010年，公司新进入昆明，贵阳，清远，温州，扬州，南通，嘉兴，唐山，廊坊，吉林，抚顺，乌鲁木齐等12个城市，截止2011年中末，公司进入的城市数量已经达到50个。</p><p>要点七：<font>住宅产业化</font>　董事长王石表示万科2010年将全面开展精装修住宅，不再有毛坯房，到2014年完全实现住宅产业化，并把住宅产业化作为推广绿色住宅的平台，加大低碳地产与绿色建筑的投资建设力度。到2015年，万科不会再用传统的施工方法，届时的开工量将达到1300万—1500万平方米。2010年万科计划实现180万平方米的工业化建筑规模。</p><p>要点八：<font>绿色企业</font>　2010年年报披露，2011年，公司预计完成200万平方米工业化项目开工目标，并在北京着手建立第二个建筑技术研究基地。公司将积极推动绿色节能技术的研发和应用，推动向绿色企业的转型。2011年的新开工项目中，预计参照绿色建筑标准的面积将达到180万平方米。</p><p>要点九：<font>股权激励</font>　2011年3月，对股权激励计划修订，拟向激励对象授予1.1亿份股票期权。其中董事会主席王石数量为660万股，总裁郁亮为550万股，行权价格8.89元(由于2010年10派1元。行权价格调整为8.79元。)。股票期权有效期为5年。授予的股票期权于授权日开始，经过一年的等待期，在之后的三个行权期，第一，第二和第三个行权期分别有40%，30%，30%的期权在满足业绩条件前提下获得可行权的权利。第一个行权期为自授权日起12个月后的首个交易日起至授权日起36个月的最后一个交易日当日止，第二个行权期为自授权日起24个月后的首个交易日起至授权日起48个月的最后一个交易日当日止，第三个行权期自授权日起36个月后的首个交易日起至授权日起60个月的最后一个交易日当日止。行权条件：第一个行权期：T年ROE不低于14%，T年较T-1年的净利润增长率不低于20%，第二个行权期：T+1年ROE不低于14.5%，T+1年较T-1年的净利润增长率不低于45%，第三个行权期：T+2年ROE不低于15%，T+2年较T-1年的净利润增长率不低于75%。根据计算公司本次授予的第一，二，三个行权期的股票期权理论价值为2.814元，3.289元和3.712元。本次期权的总理论价值为35484.9万元。</p></div>";
		String s2 = "要点一：所属板块　QFII重仓板块，基金重仓板块";
		String s3 = "this is a war";
		System.out.println(filterContent(s, " 000111 "));
	}
	
	
	
}

