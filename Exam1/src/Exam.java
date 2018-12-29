import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Exam {

	public static void main(String[] arg){
		String sformatx="";
		String sformat="";
		double result1=0;
		double result2=0;
		double jc[][]=new double [10][10];
		int xi[]=new int[10];
		double fxi[]=new double[10];
		//初始化
		for(int i=0;i<10;i++){
			xi[i]=1994+i;
		}
		//初始化
		fxi[0]=67.052;
		fxi[1]=68.008;
		fxi[2]=69.803;
		fxi[3]=72.024;
		fxi[4]=73.400;
		fxi[5]=72.063;
		fxi[6]=74.669;
		fxi[7]=74.487;
		fxi[8]=74.065;
		fxi[9]=76.777;
		//求均差
		for(int j=0;j<10;j++){
			if(j==0){
				System.out.println((j+1)+"阶");
			for(int i=1;i<10;i++){
			jc[0][i]=(double)(fxi[i]-fxi[i-1])/(xi[i]-xi[i-1]);
			BigDecimal  b  =new BigDecimal(jc[j][i]);
			double f1  =  b.setScale(3,  RoundingMode.HALF_UP).doubleValue();
			System.out.println(f1);
				}
			}
			else{
				System.out.println((j+1)+"阶");
				for(int i=j+1;i<10;i++){
					jc[j][i]=(double)(jc[j-1][i]-jc[j-1][i-1])/(j+1);
					BigDecimal  b  =new BigDecimal(jc[j][i]);
					double f1  =  b.setScale(111,  RoundingMode.HALF_UP).doubleValue();
					System.out.println(f1);
						}
			}
		
		}
		//输出pn(x)
		System.out.print("p9(x)="+fxi[0]
				+"\n+"+jc[0][1]+"*(x -1994)"
				+"\n+"+jc[1][2]+"*(x -1994)*(x -1995)"
				+"\n+"+jc[2][3]+"*(x -1994)*(x -1995)*(x -1996)"
				+"\n+"+jc[3][4]+"*(x -1994)*(x -1995)*(x -1996)*(x -1997)"
				+"\n+"+jc[4][5]+"*(x -1994)*(x -1995)*(x -1996)*(x -1997)*(x -1998)"
				+"\n+"+jc[5][6]+"*(x -1994)*(x -1995)*(x -1996)*(x -1997)*(x -1998)*(x -1999)"
				+"\n+"+jc[6][7]+"*(x -1994)*(x -1995)*(x -1996)*(x -1997)*(x -1998)*(x -1999)*(x -2000)"
				+"\n+"+jc[7][8]+"*(x -1994)*(x -1995)*(x -1996)*(x -1997)*(x -1998)*(x -1999)*(x -2000)*(x -2001)"
				+"\n+"+jc[8][9]+"*(x -1994)*(x -1995)*(x -1996)*(x -1997)*(x -1998)*(x -1999)*(x -2000)*(x -2001)*(x -2002)\n");
//		for(int i=0;i<10;i++){
//			for(int j=0;j<10;j++){
//				System.out.println(jc[i][j]);
//			}
//		}
//		result1=fxi[0]+jc[0][1]*16+jc[1][2]*16*15+jc[2][3]*16*15*14+
//				jc[3][4]*16*15*14*13+jc[4][5]*16*15*14*13*12+
//				jc[5][6]*16*15*14*13*12*11+jc[6][7]*16*15*14*13*12*11*10+jc[7][8]*16*15*14*13*12*11*10*9+jc[8][9]*16*15*14*13*12*11*10*9*8;
//		System.out.println(result1);
//		System.out.println("  ");
//		System.out.println("二阶");
//		for(int i=2;i<10;i++){
//			jc[1][i]=(double)(jc[0][i]-jc[0][i-1])/2;
//			BigDecimal  b  =new BigDecimal(jc[1][i]);
//			double f1  =  b.setScale(5,  RoundingMode.HALF_UP).doubleValue();
//			System.out.println(f1);
//		}
//		System.out.println("三阶");
//		for(int i=3;i<10;i++){
//			jc[2][i]=(double)(jc[1][i]-jc[1][i-1])/3;
//			BigDecimal  b  =new BigDecimal(jc[2][i]);
//			double f1  =  b.setScale(10,  RoundingMode.HALF_UP).doubleValue();
//			System.out.println(f1);
//		}
//		for(int i=0;i<10;i++){
//			double fm=1;
//			double fms=1;
//			for(int j=0;j<10;j++){
//				if(i!=j){
//					fm=(xi[i]-xi[j])*fm;
//					fms=(2010-xi[j])*fms;
//				}
//			}
//			double fs=(double)fxi[i]/fm;
//			for(int j=0;j<10;j++){
//				if(i!=j){
//					sformatx=sformatx+"(x-"+Integer.toString(xi[j])+")";
//				}
//			}
//			result2=result2+fs*fms;
//			System.out.println(result2);
//			if(i!=9){
//				sformat=sformat+Double.toString(fxi[i])+"/"+Double.toString(fm)+sformatx+"+";
//			}
//			else{
//				sformat=sformat+Double.toString(fxi[i])+"/"+Double.toString(fm)+sformatx;
//			}
//			sformatx="";
//			fm=1;
//			fms=1;
//		}
//		System.out.println(sformat);
//		System.out.println(result2);
		
		//绘制函数值、图像及预测值
		XYSeries series = new XYSeries("xySeries");
		for (int x = 0; x < 10; x ++) {
			double y = fxi[0]
					+jc[0][1]*(x+1994-1994)
					+jc[1][2]*(x+1994-1994)*(x+1994-1995)
					+jc[2][3]*(x+1994-1994)*(x+1994-1995)*(x+1994-1996)
					+jc[3][4]*(x+1994-1994)*(x+1994-1995)*(x+1994-1996)*(x+1994-1997)
					+jc[4][5]*(x+1994-1994)*(x+1994-1995)*(x+1994-1996)*(x+1994-1997)*(x+1994-1998)
					+jc[5][6]*(x+1994-1994)*(x+1994-1995)*(x+1994-1996)*(x+1994-1997)*(x+1994-1998)*(x+1994-1999)
					+jc[6][7]*(x+1994-1994)*(x+1994-1995)*(x+1994-1996)*(x+1994-1997)*(x+1994-1998)*(x+1994-1999)*(x+1994-2000)
					+jc[7][8]*(x+1994-1994)*(x+1994-1995)*(x+1994-1996)*(x+1994-1997)*(x+1994-1998)*(x+1994-1999)*(x+1994-2000)*(x+1994-2001)
					+jc[8][9]*(x+1994-1994)*(x+1994-1995)*(x+1994-1996)*(x+1994-1997)*(x+1994-1998)*(x+1994-1999)*(x+1994-2000)*(x+1994-2001)*(x+1994-2002);
			System.out.println("p9("+(x+1994)+")="+y);
			series.add(x+1994, y);
		}
		double xx=2010;
		double y2010 = fxi[0]
				+jc[0][1]*(xx-1994)
				+jc[1][2]*(xx -1994)*(xx -1995)
				+jc[2][3]*(xx -1994)*(xx -1995)*(xx -1996)
				+jc[3][4]*(xx -1994)*(xx -1995)*(xx -1996)*(xx -1997)
				+jc[4][5]*(xx-1994)*(xx -1995)*(xx -1996)*(xx -1997)*(xx -1998)
				+jc[5][6]*(xx -1994)*(xx -1995)*(xx -1996)*(xx -1997)*(xx -1998)*(xx -1999)
				+jc[6][7]*(xx -1994)*(xx -1995)*(xx -1996)*(xx -1997)*(xx -1998)*(xx -1999)*(xx -2000)
				+jc[7][8]*(xx -1994)*(xx -1995)*(xx -1996)*(xx -1997)*(xx -1998)*(xx-1999)*(xx -2000)*(xx -2001)
				+jc[8][9]*(xx -1994)*(xx -1995)*(xx -1996)*(xx -1997)*(xx -1998)*(xx -1999)*(xx -2000)*(xx -2001)*(xx -2002);
		System.out.println("预测结果：p9(2010)="+y2010);
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		JFreeChart chart = ChartFactory.createScatterPlot(
				"Oil production chart", // chart title
				"year", // x axis label
				"Oil production", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL,
				false, // include legend
				false, // tooltips
				false // urls
				);
 
		ChartFrame frame = new ChartFrame("Figure 1", chart);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
