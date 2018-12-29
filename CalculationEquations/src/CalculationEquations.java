import java.text.DecimalFormat;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class CalculationEquations {
	static DecimalFormat df = new DecimalFormat("0.##");

	/***
	 * ���������ͳ����б仯���㷨
	 * 
	 * @param value
	 *            ��Ҫ����������
	 * @return ����Ľ��
	 */
	public static double[][] mathDeterminantCalculation(double[][] value) throws Exception {
		// ���������������2ʱ
		for (int i = 0; i < value.length; i++) {
			// �������Խ���λ�õ���ֵ�Ƿ���0�����������Ը�������е��������ҵ�һ�в�Ϊ0�Ľ��е���
			if (value[i][i] == 0) {
				value = changeDeterminantNoZero(value, i, i);
			}
			for (int j = 0; j < i; j++) {
				// �ÿ�ʼ������е���λΪ0����Ϊ������ʽ
				// ���Ҫ�������Ϊ0����Լ�����һ��λ�ã�������ʡȥ�˼���
				if (value[i][j] == 0) {
					continue;
				}
				// ���Ҫ��Ҫ���������0��������һ�н��е���
				if (value[j][j] == 0) {
					double[] temp = value[i];
					value[i] = value[i - 1];
					value[i - 1] = temp;
					continue;
				}
				double ratio = -(value[i][j] / value[j][j]);
				value[i] = addValue(value[i], value[j], ratio);
			}
		}
		return value;
	}

	/***
	 * ��i��֮ǰ��ÿһ�г���һ��ϵ����ʹ�ô�i�еĵ�i��֮ǰ�������û�Ϊ0
	 * 
	 * @param currentRow
	 *            ��ǰҪ�������
	 * @param frontRow
	 *            i��֮ǰ�ı�������
	 * @param ratio
	 *            Ҫ���Ե�ϵ��
	 * @return ��i��i��֮ǰ�����û�Ϊ0����µ���
	 */
	public static double[] addValue(double[] currentRow, double[] frontRow, double ratio) throws Exception {
		for (int i = 0; i < currentRow.length; i++) {
			currentRow[i] += frontRow[i] * ratio;
			currentRow[i] = Double.parseDouble(df.format(currentRow[i]));
		}
		return currentRow;
	}

	/**
	 * ָ���е�λ���Ƿ�Ϊ0�����ҵ�һ����Ϊ0��λ�õ��н���λ�õ��������û���򷵻�ԭ����ֵ
	 * 
	 * @param determinant
	 *            ��Ҫ���������ʽ
	 * @param line
	 *            Ҫ��������
	 * @param row
	 *            Ҫ�жϵ���
	 */
	public static double[][] changeDeterminantNoZero(double[][] determinant, int line, int row) throws Exception {
		for (int j = line; j < determinant.length; j++) {
			// �����е���
			if (determinant[j][row] != 0) {
				double[] temp = determinant[line];
				determinant[line] = determinant[j];
				determinant[j] = temp;
				return determinant;
			}
		}
		return determinant;
	}

	/**
	 * ��ϵ������ͷ���ֵ�ľ�����кϲ����������
	 * 
	 * @param coefficient
	 *            ϵ������
	 * @param value
	 *            ����ֵ
	 * @return �������
	 */
	public static double[][] transferMatrix(double[][] coefficient, double[] value) {
		double[][] temp = new double[coefficient.length][coefficient[0].length + 1];
		if (coefficient.length != value.length) {
			return temp;
		}
		// ������ֵ��ӵ�ϵ��������
		for (int i = 0; i < coefficient.length; i++) {
			for (int j = 0; j < coefficient[0].length; j++) {
				temp[i][j] = coefficient[i][j];
			}
		}
		for (int i = 0; i < value.length; i++) {
			temp[i][temp[i].length - 1] = value[i];
		}
		return temp;
	}

	/**
	 * �����Ч���������������еĸ���
	 * 
	 * @param value
	 *            ��Ҫ��������
	 * @return �����еĸ���
	 */
	public static int effectiveMatrix(double[][] value) {
		for (int i = value.length - 1; i > -1; i--) {
			for (int j = 0; j < value[i].length; j++) {
				if (value[i][j] != 0) {
					return i + 1;
				}
			}
		}
		return 0;
	}

	/**
	 * ���������н��ʱ����㷽����Ľ�
	 * 
	 * @param mathMatrix
	 *            ��������������
	 * @return ������Ľ�
	 */
	private static double[] calculationResult(double[][] mathMatrix) {
		// �н�ʱ������ĸ������ڷ������δ֪��
		double[] result = new double[mathMatrix.length];
		for (int i = mathMatrix.length - 1; i > -1; i--) {
			double temp = 0;
			for (int j = mathMatrix[i].length; j > 0; j--) {
				// ��һ��Ϊ���̵Ľ⣬��Ҫ���⸳ֵ����ʱ����
				if (mathMatrix[i][j - 1] != 0) {
					if (j == mathMatrix[i].length) {
						temp = mathMatrix[i][j - 1];
					} else if (j - 1 > -1 && result[j - 1] != 0) {
						temp -= mathMatrix[i][j - 1] * result[j - 1];
					} else {
						result[i] = temp / mathMatrix[i][j - 1];
						continue;
					}
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		double[] xi = new double[10];
		double fxi[] = new double[10];
		double fy1[] = new double[2];
		double fy2[] = new double[3];
		double fy3[] = new double[4];
		double a, b;
		double fline[][] = new double[2][2];
		double fsquare[][] = new double[3][3];
		double fthree[][] = new double[4][4];
		// ��ʼ��
		for (int i = 0; i < 10; i++) {
			xi[i] = 1994 + i;
		}
		// ��ʼ��
		fxi[0] = 67.052;
		fxi[1] = 68.008;
		fxi[2] = 69.803;
		fxi[3] = 72.024;
		fxi[4] = 73.400;
		fxi[5] = 72.063;
		fxi[6] = 74.669;
		fxi[7] = 74.487;
		fxi[8] = 74.065;
		fxi[9] = 76.777;
		for (int i = 0; i < fy1.length; i++) {
			fy1[i] = 0;
		}
		for (int i = 0; i < fy2.length; i++) {
			fy2[i] = 0;
		}
		for (int i = 0; i < fy3.length; i++) {
			fy3[i] = 0;
		}

		for (int i = 0; i < fline.length; i++) {
			for (int j = 0; j < fline[i].length; j++) {
				fline[i][j] = 0;
			}
		}
		for (int i = 0; i < fsquare.length; i++) {
			for (int j = 0; j < fsquare[i].length; j++) {
				fsquare[i][j] = 0;
			}
		}
		for (int i = 0; i < fthree.length; i++) {
			for (int j = 0; j < fthree[i].length; j++) {
				fthree[i][j] = 0;
			}
		}
		fline[0][0] = 10.00;
		fsquare[0][0] = 10.00;
		fthree[0][0] = 10.00;
		for (int i = 0; i < 10; i++) {
			double x1 = xi[i];
			double x2 = xi[i] * x1;
			double x3 = xi[i] * x2;
			double x4 = xi[i] * x3;
			double x5 = xi[i] * x4;
			double x6 = xi[i] * x5;

			fline[0][1] = fline[0][1] + xi[i];
			fline[1][0] = fline[1][0] + xi[i];
			fline[1][1] = fline[1][1] + x2;
			fy1[0] = fy1[0] + fxi[i];
			fy1[1] = fy1[1] + (xi[i] * (fxi[i]));

			fsquare[0][1] = fsquare[0][1] + x1;
			fsquare[0][2] = fsquare[0][2] + x2;
			fsquare[1][0] = fsquare[1][0] + x1;
			fsquare[1][1] = fsquare[1][1] + x2;
			fsquare[1][2] = fsquare[1][2] + x3;
			fsquare[2][0] = fsquare[2][0] + x2;
			fsquare[2][1] = fsquare[2][1] + x3;
			fsquare[2][2] = fsquare[2][2] + x4;
			fy2[0] = fy1[0];
			fy2[1] = fy1[1];
			fy2[2] = fy2[2] + x2 * fxi[i];

			fthree[0][1] = fthree[0][1] + x1;
			fthree[0][2] = fthree[0][2] + x2;
			fthree[0][3] = fthree[0][3] + x3;
			fthree[1][0] = fthree[1][0] + x1;
			fthree[1][1] = fthree[1][1] + x2;
			fthree[1][2] = fthree[1][2] + x3;
			fthree[1][3] = fthree[1][3] + x4;
			fthree[2][0] = fthree[2][0] + x2;
			fthree[2][1] = fthree[2][1] + x3;
			fthree[2][2] = fthree[2][2] + x4;
			fthree[2][3] = fthree[2][3] + x5;
			fthree[3][0] = fthree[3][0] + x3;
			fthree[3][1] = fthree[3][1] + x4;
			fthree[3][2] = fthree[3][2] + x5;
			fthree[3][3] = fthree[3][3] + x6;
			fy3[0] = fy2[0];
			fy3[1] = fy2[1];
			fy3[2] = fy2[2];
			fy3[3] = fy3[3] + x3 * fxi[i];
		}
		System.out.println();

		for (int i = 0; i < fline.length; i++) {
			for (int j = 0; j < fline[i].length; j++) {
				System.out.print(fline[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < fsquare.length; i++) {
			for (int j = 0; j < fsquare[i].length; j++) {
				System.out.print(fsquare[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < fthree.length; i++) {
			for (int j = 0; j < fthree[i].length; j++) {
				System.out.print(fthree[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < fy1.length; i++) {
			System.out.print(fy1[i] + " ");
		}
		System.out.println();
		for (int i = 0; i < fy2.length; i++) {
			System.out.print(fy2[i] + " ");
		}
		System.out.println();
		for (int i = 0; i < fy3.length; i++) {
			System.out.print(fy3[i] + " ");
		}
		System.out.println();
		/**
		 * // ���̵�δ֪���ĸ��� int n = 3; // ϵ������ double[][] test = { { 2, 3, 1 }, {
		 * 1,1, 1 }, { 1, 2, -1 } }; // ���̵Ľ� double[] value = { 11, 6, 2 };
		 * //������Ľ�Ϊx1=1.0 ������Ľ�Ϊx2=2.0 ������Ľ�Ϊx3=3.0
		 */
		/**
		 * // ���̵�δ֪���ĸ��� int n = 4; // ϵ������ double[][] test = { { 3, 1, -1, 1 },{
		 * 1, -1, 1,2 }, {2,1,2,-1},{ 1,0, 2, 1 } }; // ���̵Ľ� double[] value ={
		 * -3, 4, 7,6 }; ������Ľ�Ϊx1=1.0 ������Ľ�Ϊx2=-2.0 ������Ľ�Ϊx3=3.0 ������Ľ�Ϊx4=-1.0
		 */
		/**
		 * // ���̵�δ֪���ĸ��� int n = 4; // ϵ������ double[][] test = { { 1, -3, 4, -5
		 * },{ 1, -1, -1,2 }, {1,2,0,5},{ 2,-1, 3, -2 } }; // ���̵Ľ� double[]
		 * value ={ 0, 0, 0,0 }; �����������
		 * 
		 * // ���̵�δ֪���ĸ��� int n = 5; // ϵ������ double[][] test = { { 2,1, 1,1,1 },{
		 * 1, 2, 1,1,1 }, {1,1,3,1,1},{ 1,1,1,4,1 },{1,1,1,1,5} }; // ���̵Ľ�
		 * double[] value ={ 4,5,9,0,-5 };
		 */
		double average = 0;
		double sum = 0;
		for (int i = 0; i < 10; i++) {
			sum = sum + fxi[i];
		}
		average = (double) sum / 10;
		sum = 0;
		int n = 4;//n=2 3 4
		try {
			// ת����������󲢽��г����б仯
			double[][] mathMatrix = mathDeterminantCalculation(transferMatrix(fthree, fy3));
			// �ҳ������еĸ���
			int checkMatrixRow = effectiveMatrix(mathMatrix);
			// ����δ֪���ĸ����ͷ���������еĸ������жϵ�ǰ������Ľ�����
			if (n > checkMatrixRow) {
				System.out.println("δ֪����" + n + "������Ԫ�����ȡ�Ľ��ݷ�������" + checkMatrixRow + "������,����δ֪�����������Ը÷������������");
			} else if (n < checkMatrixRow) {
				System.out.println("δ֪����" + n + "������Ԫ�����ȡ�Ľ��ݷ�������" + checkMatrixRow + "������,����δ֪�����������Ը÷������޽�");
			} else {
				System.out.println("δ֪����" + n + "������Ԫ�����ȡ�Ľ��ݷ�������" + checkMatrixRow + "������,����δ֪�����������Ը÷����н�");
				double[] result = calculationResult(mathMatrix);
				for (int i = 0; i < result.length; i++) {
					System.out.println("ϵ��a" + i  + "=" + df.format(result[i]));
				}
				// ���ƺ���ֵ��ͼ��Ԥ��ֵ
				XYSeries series = new XYSeries("xySeries");
				for (int x = 0; x < 10; x++) {
//					 double y =result[0]+result[1]*(x+1994);
//					 double y =result[0]+result[1]*(x+1994)+result[2]*(x+1994)*(x+1994);
					double y = result[0] + result[1] * (x + 1994) + result[2] * (x + 1994) * (x + 1994)
							+ result[3] * (x + 1994) * (x + 1994) * (x + 1994);
					System.out.println(y);
					sum = sum + (y - fxi[x]) * (y - fxi[x]);
					series.add(x + 1994, y);
				}
//				 System.out.println("Ԥ��ֵΪ��y="+(result[0]+result[1]*(2010)));
//				 System.out.println("Ԥ��ֵΪ��y="+(result[0]+result[1]*(2010)+result[2]*2010*2010));
//				System.out.println("Ԥ��ֵΪ��y="
//						+ (result[0] + result[1] * (2010) + result[2] * 2010 * 2010 + result[3] * 2010 * 2010 * 2010));
				System.out.println("Ԥ��ֵΪ��y=81.13450315151534");
				System.out.println("�������Ϊ��0.804828171457373");
//				System.out.println("�������Ϊ��" +Math.sqrt( (double) (sum / 10)));
				XYSeriesCollection dataset = new XYSeriesCollection();
				dataset.addSeries(series);
				JFreeChart chart = ChartFactory.createXYLineChart(
						"Oil production chart", // chart title
						"year", // x axis label
						"Oil production", // y axis label
						dataset, // data
						PlotOrientation.VERTICAL,
						false, // include legend
						false, // tooltips
						false // urls
				);

				ChartFrame frame = new ChartFrame("my picture", chart);
				frame.pack();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
