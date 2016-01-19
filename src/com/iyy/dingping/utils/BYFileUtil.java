package com.iyy.dingping.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import org.apache.http.util.EncodingUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.view.WindowManager;
import android.widget.LinearLayout;
import cn.bmob.push.lib.util.LogUtil;

public class BYFileUtil {
	
	public static String packageName;

	public static void SetPackageName(Context context) {
		packageName = context.getPackageName();
	}

	public static String GetBasePackagePath() {
		String path = "/data/data/" + packageName + "/";
		return path;
	}

	/**
	 * ��ȡָ����Դ�ļ��ڵ��ı�
	 * @param context
	 * @param resID
	 * @return
	 */
	public static String getFileText(Context context,int resID) {
		String res = "";

		try {
			// �õ���Դ�е�Raw������
			InputStream in = context.getResources().openRawResource(resID);
			// �õ����ݵĴ�С
			int length = in.available();
			byte[] buffer = new byte[length];
			// ��ȡ����
			in.read(buffer);
			// ���ļ��ı�������ѡ����ʵı��룬���������������
			res = EncodingUtils.getString(buffer,"UTF-8");
			// �ر�
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static void copyFileToDocument(Context context,int resID,String fileName)
	{
		try {
			
			if (IsFileExist(fileName)) {
				return;
			}
			// �õ���Դ�е�Raw������
			InputStream in = context.getResources().openRawResource(resID);
			// �õ����ݵĴ�С
			int length = in.available();
			byte[] buffer = new byte[length];
			// ��ȡ����
			in.read(buffer);
			
			 FileOutputStream outStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);    
		        outStream.write(buffer);    
		        outStream.close(); 
			
			// �ر�
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �õ���ǰ�������ڵ�ͨ���ļ�����Ŀ¼
	 * @return
	 */
	public static String GetBaseDocumentPath()
	{
		String path = "/data/data/" + packageName + "/files/";
		return path;
	}
	
	/**
	 * �ж��ļ��Ƿ����
	 * @param filePath
	 * @return
	 */
	public static boolean IsFileExist(String filePath){
		File file=new File(filePath);
		return file.exists();
	}
	
	/**
	 * ɾ���ļ��������ļ�����ʱ��
	 * @param filePath
	 */
	public static void DeleteFileWhenExist(String filePath)
	{
		File file=new File(filePath);
		if (file.exists()) {
			file.delete();
		}
	}
	
	/**
	 * �ж��ļ����Ƿ���ڣ�������ʱ����
	 * @param strFolderName
	 */
	public static void CreateFolderWhenNotExist(String strFolderName)
	{
		File filesFolder=new File(strFolderName);
		if(!filesFolder.exists())
		{
			filesFolder.mkdirs();
		}
	}

	
//	public static  String getRealPathFromURI(Context context,Uri contentURI) {
//		Cursor cursor = context.getContentResolver().query(contentURI, null, null,
//				null, null);
//		cursor.moveToFirst();
//		int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//		return cursor.getString(idx);
//	}
	
	public static String saveFileFromUrl(Context context,int usrSysID,String imageurl){
		String filename = getFilenameForUrl(usrSysID,imageurl);
		AndroidHttpClient client = AndroidHttpClient.newInstance(context.getPackageName());
		try {
			File file = context.getFileStreamPath(filename);
	        if (!file.exists()) {
	        	URL url = new URL(imageurl);
	        	//ʵ����URL��openConnection�ķ���ֵ����һ��URLConnection
	        	   URLConnection connect = url.openConnection();
	        	   connect.connect();
	        	   InputStream input = connect.getInputStream();

	        	//����һ�������
	        	   OutputStream out = new BufferedOutputStream(new FileOutputStream(file));

	        	//ÿ�ε�д���СΪ��
	        	   int length=1024*1024;
	        	   byte[] a = new  byte[length];

	        	//�����С����length����ѭ��������ֵΪ-1
	        	   while((length=input.read(a))>0){
	        	    out.write(a, 0, length);
	        	   }
	        	//�����˹ر���
	        	   input.close();
	        	   out.close();
	        }
	        return file.getPath();
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
            client.close();
        }
		return "";
	}
	
	public static String saveFileFromBitmap(Context context,Bitmap bitmap){
		String filename = new Date().getTime()+".png";
		 BufferedOutputStream bos = null;  
		try {
			File file = new File("/sdcard/" + filename);
	        if (!file.exists()) {
	             bos = new BufferedOutputStream(new FileOutputStream(file));
                 bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                 bos.flush();
                 bos.close();
                 return file.getPath();
	        }
		}catch (Exception e) {
		}finally{
			try {
				if(null!=bos){
					bos.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	//��ȡurl�ļ���
	public static String getFilenameForUrl(int usrSysID,String url) {
        return usrSysID + url.hashCode() + ".png";
    }
	public static String saveFileFromUrlMP3(Context context,int usrSysID,String imageurl){
		String filename = getFilenameForUrlMP3(usrSysID,imageurl);
		AndroidHttpClient client = AndroidHttpClient.newInstance(context.getPackageName());
		try {
			File file = new File("/sdcard/"+filename);
	        if (!file.exists()) {
	        	URL url = new URL(imageurl);
	        	//ʵ����URL��openConnection�ķ���ֵ����һ��URLConnection
	        	   URLConnection connect = url.openConnection();
	        	   connect.connect();
	        	   InputStream input = connect.getInputStream();

	        	//����һ�������
	        	   OutputStream out = new BufferedOutputStream(new FileOutputStream(file));

	        	//ÿ�ε�д���СΪ��
	        	   int length=1024*1024;
	        	   byte[] a = new  byte[length];

	        	//�����С����length����ѭ��������ֵΪ-1
	        	   while((length=input.read(a))>0){
	        	    out.write(a, 0, length);
	        	   }
	        	//�����˹ر���
	        	   input.close();
	        	   out.close();
	        }
	        return file.getPath();
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
            client.close();
        }
		return "";
	}
	//��ȡurl�ļ���
	public static String getFilenameForUrlMP3(int usrSysID,String url) {
        return usrSysID + url.hashCode() + ".mp3";
    }
	public static int copyStream(InputStream input, OutputStream output) throws IOException
    {
        byte[] stuff = new byte[1024];
        int read = 0;
        int total = 0;
        while ((read = input.read(stuff)) != -1)
        {
            output.write(stuff, 0, read);
            total += read;
        }
        return total;
    }
	
	public static Bitmap readBitmapAutoSize(String filePath) {    
        //outWidth��outHeight��Ŀ��ͼƬ������Ⱥ͸߶ȣ���������  
		FileInputStream fs = null;  
		BufferedInputStream bs = null;  
		try {  
		    fs = new FileInputStream(filePath);  
		    bs = new BufferedInputStream(fs);  
		    return BitmapFactory.decodeStream(bs);  
		} catch (Exception e) {  
		} finally {  
		    try {  
		        bs.close();  
		        fs.close();  
		    } catch (Exception e) {  
		    }  
		}  
		return null;  
	} 
	
	public static Bitmap readBitmapAutoSize(Context context,String filePath) {    
        //outWidth��outHeight��Ŀ��ͼƬ������Ⱥ͸߶ȣ���������  
	FileInputStream fs = null;  
	BufferedInputStream bs = null;  
	try {  
	    fs = new FileInputStream(filePath);  
	    bs = new BufferedInputStream(fs);  
	    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
	    int width = wm.getDefaultDisplay().getWidth();
	    BitmapFactory.Options options = setBitmapOption(filePath, DpDipConversionUtil.px2dip(context, width)-20, 900);  
	    return BitmapFactory.decodeStream(bs, null, options);  
	} catch (Exception e) {  
	    e.printStackTrace();  
	} finally {  
	    try {  
	    	if(null!=bs){
	    		 bs.close();  
	    	}
	    	if(null!=fs){
	    		 fs.close(); 
	    	}
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	}  
	return null;  
	}  
	
    public static Bitmap readBitmapAutoSize1(Context context,String filePath) {  
        //outWidth��outHeight��Ŀ��ͼƬ������Ⱥ͸߶ȣ���������  
	FileInputStream fs = null;  
	BufferedInputStream bs = null;  
	try {  
	    fs = new FileInputStream(filePath);  
	    bs = new BufferedInputStream(fs);  
	    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
	    int width = wm.getDefaultDisplay().getWidth();
	    BitmapFactory.Options options = setBitmapOption(filePath, DpDipConversionUtil.px2dip(context, width)-20, 900);  
	    return BitmapFactory.decodeStream(bs, null, options);  
	} catch (Exception e) {  
	    e.printStackTrace();  
	} finally {  
	    try {  
	        bs.close();  
	        fs.close();  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	}  
	return null;  
	}  
	
    public static Bitmap readBitmapAutoSize1(Context context,String filePath,int width, int height) {    
        //outWidth��outHeight��Ŀ��ͼƬ������Ⱥ͸߶ȣ���������  
	FileInputStream fs = null;  
	BufferedInputStream bs = null;  
	try {  
	    fs = new FileInputStream(filePath);  
	    bs = new BufferedInputStream(fs);  
	    BitmapFactory.Options options = setBitmapOption(filePath, width, height);  
	    return BitmapFactory.decodeStream(bs, null, options);  
	} catch (Exception e) {  
	    e.printStackTrace();  
	} finally {  
	    try {  
	        bs.close();  
	        fs.close();  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	}  
	return null;  
	}  
    
	private static BitmapFactory.Options setBitmapOption(String file, int width, int height) {  
	BitmapFactory.Options opt = new BitmapFactory.Options();  
	opt.inJustDecodeBounds = true;            
	        //����ֻ�ǽ���ͼƬ�ı߾࣬�˲���Ŀ���Ƕ���ͼƬ��ʵ�ʿ�Ⱥ͸߶�  
	BitmapFactory.decodeFile(file, opt);  
	
	int outWidth = opt.outWidth; //���ͼƬ��ʵ�ʸߺͿ�  
	int outHeight = opt.outHeight;  
	
	opt.inDither = false;  
	opt.inPreferredConfig = Bitmap.Config.RGB_565;      
	        //���ü���ͼƬ����ɫ��Ϊ16bit��Ĭ����RGB_8888����ʾ24bit��ɫ��͸��ͨ������һ���ò���  
//	opt.inSampleSize = computeSampleSize(opt, -1, 128*128);;                            
	        //�������ű�,1��ʾԭ������2��ʾԭ�����ķ�֮һ....  
	        //�������ű�  
	int sampleSize = 1;
	if (outWidth != 0 && outHeight != 0 && width > 0 && height != 0) {  
	    sampleSize = (outWidth / width + outHeight / height) / 2;
	    
	    if(sampleSize==0){
	    	sampleSize = 1;
	    }
	    
	    opt.inSampleSize = sampleSize;  
	}  
	
	opt.inJustDecodeBounds = false;//���ѱ�־��ԭ  
	return opt;  
	}  
  
	
	public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {  
	    int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);  
	    int roundedSize;  
	    if (initialSize <= 8) {  
	        roundedSize = 1;  
	        while (roundedSize < initialSize) {  
	            roundedSize <<= 1;  
	        }  
	    } else {  
	        roundedSize = (initialSize + 7) / 8 * 8;  
	    }  
	    return roundedSize;  
	}  
	  
	private static int computeInitialSampleSize(BitmapFactory.Options options,int minSideLength, int maxNumOfPixels) {  
	    double w = options.outWidth;  
	    double h = options.outHeight;  
	    int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));  
	    int upperBound = (minSideLength == -1) ? 128 :(int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));  
	    if (upperBound < lowerBound) {  
	        // return the larger one when there is no overlapping zone.  
	        return lowerBound;  
	    }  
	    if ((maxNumOfPixels == -1) && (minSideLength == -1)) {  
	        return 1;  
	    } else if (minSideLength == -1) {  
	        return lowerBound;  
	    } else {  
	        return upperBound;  
	    }  
	}  
	
    public static LinearLayout.LayoutParams getLayoutParams(Context context,Bitmap bitmap,int screenWidth){  
        
        float rawWidth = bitmap.getWidth();  
        float rawHeight = bitmap.getHeight();  
          
        float width = 0;  
        float height = 0;  
  
//        Log.i("hello", "ԭʼͼƬ�߶ȣ�"+rawHeight+"ԭʼͼƬ��ȣ�"+rawWidth);  
//        Log.i("hello", "ԭʼ�߿�ȣ�"+(rawHeight/rawWidth));  
          
        if(rawWidth > screenWidth){  
            height = (rawHeight/rawWidth)*screenWidth;  
            width = screenWidth;  
        }else{  
            width = rawWidth;  
            height = rawHeight;  
        }  
          
        //Log.i("hello", "�����ͼƬ�߶ȣ�"+height+"�����ͼƬ��ȣ�"+width);  
          
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int)width-DpDipConversionUtil.dip2px(context, 20), (int)height);  
          
        return layoutParams;  
    }  
	
}
