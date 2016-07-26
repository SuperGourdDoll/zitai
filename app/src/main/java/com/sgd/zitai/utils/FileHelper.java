package com.sgd.zitai.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileHelper {
	
	private static final int FILE_BUFFER_SIZE = 51200;
	/**
	 * 系统图片缓存路径
	 */
	public static String getImageCachePath(Context context) {
		String cacheDir = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
				|| !Environment.isExternalStorageRemovable()) {
			cacheDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/imageascche";
		} else {
			cacheDir = context.getCacheDir().getPath() + "/imageascche";
		}
		File file = new File(cacheDir);
		if (!file.exists()) {
			file.mkdir();
		}
		LogUtil.e("当前DownloadApkCachePath缓存地址------->" + cacheDir + "/imageascche");
		return cacheDir;
	}

	/**
	 * 系统软件更新缓存路径
	 */
	public static String getDownloadApkCachePath(Context context) {

		String appCachePath = null;

		String apkDownPath = "" + System.currentTimeMillis();
		/**
		 * 内存卡是否可用
		 */
		if (checkSDCard()) {
			appCachePath = Environment.getExternalStorageDirectory() + "/AppCachePath/" + apkDownPath;
		} else {
			appCachePath = Environment.getDataDirectory().getPath() + "/AppCachePath/" + apkDownPath;
		}
		File file = new File(appCachePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		LogUtil.e("当前DownloadApkCachePath缓存地址------->" + appCachePath);
		return appCachePath;
	}

	/**
	 * 获取SD卡的路径
	 * @return
	 */
	public static String getSDPath() {
		File sdDir = null;
		if (checkSDCard()) {
			sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
		}
		if (sdDir != null) {
			return sdDir.toString();
		}
		return "";

	}

	/**
	 * 获取SD卡的路径
	 * @return
	 */
	public static boolean checkSDCard() {
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED); // 判断sd卡是否存在

		return sdCardExist;

	}

	/**
	 * 获取安装文件
	 * @return
	 */
	public static String getFilePath(String appPath, String url) {

		if (!TextUtils.isEmpty(url)) {
			int index = url.lastIndexOf("/");
			String fileName = url.substring(index, url.length());
			return appPath + "/"+fileName;
		}
		return "";
	}
	/**
	 * 判断文件是否存在
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean fileIsExist(String filePath) {
		if (filePath == null || filePath.length() < 1) {
			LogUtil.e("param invalid, filePath: " + filePath);
			return false;
		}

		File f = new File(filePath);
		if (!f.exists()) {
			return false;
		}
		return true;
	}

	/**
	 * 读取文件返回输入流
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 */
	public static InputStream readFile(String filePath) {
		if (null == filePath) {
			LogUtil.e("Invalid param. filePath: " + filePath);
			return null;
		}

		InputStream is = null;

		try {
			if (fileIsExist(filePath)) {
				File f = new File(filePath);
				is = new FileInputStream(f);
			} else {
				return null;
			}
		} catch (Exception ex) {
			LogUtil.e("Exception, ex: " + ex.toString());
			return null;
		}
		return is;
	}

	/**
	 * 读取txt文本
	 * 
	 * @param filePath
	 * @return
	 */
	public static String readFileTxt(String filePath) {
		if (null == filePath) {
			LogUtil.e("Invalid param. filePath: " + filePath);
			return null;
		}

		InputStream is = null;

		try {
			if (fileIsExist(filePath)) {
				File f = new File(filePath);
				is = new FileInputStream(f);
			} else {
				return null;
			}
		} catch (Exception ex) {
			LogUtil.e("Exception, ex: " + ex.toString());
			return null;
		}
		byte[] buffer = new byte[1024];
		String content = "";
		int i;
		try {
			i = is.read(buffer);
			while (i != -1) {
				content = content + new String(buffer, 0, i);
				i = is.read(buffer);
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return content;

	}

	/**
	 * 创建路径
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean createDirectory(String filePath) {
		if (null == filePath) {
			return false;
		}

		File file = new File(filePath);

		if (file.exists()) {
			return true;
		}

		return file.mkdirs();

	}

	/**
	 * 删除路径
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean deleteDirectory(String filePath) {
		if (null == filePath) {
			LogUtil.e("Invalid param. filePath: " + filePath);
			return false;
		}

		File file = new File(filePath);

		if (file == null || !file.exists()) {
			return false;
		}

		if (file.isDirectory()) {
			File[] list = file.listFiles();

			for (int i = 0; i < list.length; i++) {
				LogUtil.d("delete filePath: " + list[i].getAbsolutePath());
				if (list[i].isDirectory()) {
					deleteDirectory(list[i].getAbsolutePath());
				} else {
					list[i].delete();
				}
			}
		}

		LogUtil.d("delete filePath: " + file.getAbsolutePath());
		file.delete();
		return true;
	}

	/**
	 * 写文件
	 * 
	 * @param filePath
	 * @param inputStream
	 * @return
	 */
	public static boolean writeFile(String filePath, InputStream inputStream) {

		if (null == filePath || filePath.length() < 1) {
			LogUtil.e("Invalid param. filePath: " + filePath);
			return false;
		}

		try {
			File file = new File(filePath);
			if (file.exists()) {
				deleteDirectory(filePath);
			}

			String pth = filePath.substring(0, filePath.lastIndexOf("/"));
			boolean ret = createDirectory(pth);
			if (!ret) {
				LogUtil.e("createDirectory fail path = " + pth);
				return false;
			}

			file.createNewFile();
			if (!ret) {
				LogUtil.e("createNewFile fail filePath = " + filePath);
				return false;
			}

			FileOutputStream fileOutputStream = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int c = inputStream.read(buf);
			while (-1 != c) {
				fileOutputStream.write(buf, 0, c);
				c = inputStream.read(buf);
			}

			fileOutputStream.flush();
			fileOutputStream.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	/**
	 * 写文件
	 * 
	 * @param filePath
	 * @param fileContent
	 * @return
	 */
	public static boolean writeFile(String filePath, String fileContent) {
		return writeFile(filePath, fileContent, false);
	}

	public static boolean writeFile(String filePath, String fileContent, boolean append) {
		if (null == filePath || fileContent == null || filePath.length() < 1 || fileContent.length() < 1) {
			LogUtil.e("Invalid param. filePath: " + filePath + ", fileContent: " + fileContent);
			return false;
		}

		try {
			File file = new File(filePath);
			if (!file.exists()) {
				if (!file.createNewFile()) {
					return false;
				}
			}

			BufferedWriter output = new BufferedWriter(new FileWriter(file, append));
			output.write(fileContent);
			output.flush();
			output.close();
		} catch (IOException ioe) {
			LogUtil.e("writeFile ioe: " + ioe.toString());
			return false;
		}

		return true;
	}

	/**
	 * 返回文件大小
	 * 
	 * @param filePath
	 * @return
	 */
	public static long getFileSize(String filePath) {
		if (null == filePath) {
			LogUtil.e("Invalid param. filePath: " + filePath);
			return 0;
		}

		File file = new File(filePath);
		if (file == null || !file.exists()) {
			return 0;
		}

		return file.length();
	}

	/**
	 * 返回文件修改时间
	 * 
	 * @param filePath
	 * @return
	 */
	public static long getFileModifyTime(String filePath) {
		if (null == filePath) {
			LogUtil.e("Invalid param. filePath: " + filePath);
			return 0;
		}

		File file = new File(filePath);
		if (file == null || !file.exists()) {
			return 0;
		}

		return file.lastModified();
	}

	/**
	 * 设置文件修改时间
	 * 
	 * @param filePath
	 * @param modifyTime
	 * @return
	 */
	public static boolean setFileModifyTime(String filePath, long modifyTime) {
		if (null == filePath) {
			LogUtil.e("Invalid param. filePath: " + filePath);
			return false;
		}

		File file = new File(filePath);
		if (file == null || !file.exists()) {
			return false;
		}

		return file.setLastModified(modifyTime);
	}

	/**
	 * 拷贝文件
	 * 
	 * @param cr
	 *            ContentResolver
	 * @param fromPath
	 *            起始路径
	 * @param destUri
	 *            目标路径
	 * @return
	 */
	public static boolean copyFile(ContentResolver cr, String fromPath, String destUri) {
		if (null == cr || null == fromPath || fromPath.length() < 1 || null == destUri || destUri.length() < 1) {
			LogUtil.e("copyFile Invalid param. cr=" + cr + ", fromPath=" + fromPath + ", destUri=" + destUri);
			return false;
		}

		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(fromPath);
			if (null == is) {
				LogUtil.e("Failed to open inputStream: " + fromPath + "->" + destUri);
				return false;
			}

			// check output uri
			String path = null;
			Uri uri = null;

			String lwUri = destUri.toLowerCase();
			if (lwUri.startsWith("content://")) {
				uri = Uri.parse(destUri);
			} else if (lwUri.startsWith("file://")) {
				uri = Uri.parse(destUri);
				path = uri.getPath();
			} else {
				path = destUri;
			}

			// open output
			if (null != path) {
				File fl = new File(path);
				String pth = path.substring(0, path.lastIndexOf("/"));
				File pf = new File(pth);

				if (pf.exists() && !pf.isDirectory()) {
					pf.delete();
				}

				pf = new File(pth + File.separator);

				if (!pf.exists()) {
					if (!pf.mkdirs()) {
						LogUtil.e("Can't make dirs, path=" + pth);
					}
				}

				pf = new File(path);
				if (pf.exists()) {
					if (pf.isDirectory())
						deleteDirectory(path);
					else
						pf.delete();
				}

				os = new FileOutputStream(path);
				fl.setLastModified(System.currentTimeMillis());
			} else {
				os = new ParcelFileDescriptor.AutoCloseOutputStream(cr.openFileDescriptor(uri, "w"));
			}

			// copy file
			byte[] dat = new byte[1024];
			int i = is.read(dat);
			while (-1 != i) {
				os.write(dat, 0, i);
				i = is.read(dat);
			}

			is.close();
			is = null;

			os.flush();
			os.close();
			os = null;

			return true;

		} catch (Exception ex) {
			LogUtil.e("Exception, ex: " + ex.toString());
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (Exception ex) {
				}
				;
			}
			if (null != os) {
				try {
					os.close();
				} catch (Exception ex) {
				}
				;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param cr
	 * @param is
	 * @param destUri
	 * @return
	 */
	public static boolean copyFile(ContentResolver cr, InputStream is, String destUri) {
		if (null == cr || null == is || null == destUri || destUri.length() < 1) {
			LogUtil.e("copyFile Invalid param. cr=" + cr + ", , destUri=" + destUri);
			return false;
		}

		// InputStream is = null;
		OutputStream os = null;
		try {
			//
			if (null == is) {
				// LogUtil.e("Failed to open inputStream: "+fromPath+"->"+destUri);
				return false;
			}

			// check output uri
			String path = null;
			Uri uri = null;

			String lwUri = destUri.toLowerCase();
			if (lwUri.startsWith("content://")) {
				uri = Uri.parse(destUri);
			} else if (lwUri.startsWith("file://")) {
				uri = Uri.parse(destUri);
				path = uri.getPath();
			} else {
				path = destUri;
			}

			// open output
			if (null != path) {
				File fl = new File(path);
				String pth = path.substring(0, path.lastIndexOf("/"));
				File pf = new File(pth);

				if (pf.exists() && !pf.isDirectory()) {
					pf.delete();
				}

				pf = new File(pth + File.separator);

				if (!pf.exists()) {
					if (!pf.mkdirs()) {
						LogUtil.e("Can't make dirs, path=" + pth);
					}
				}

				pf = new File(path);
				if (pf.exists()) {
					if (pf.isDirectory())
						deleteDirectory(path);
					else
						pf.delete();
				}

				os = new FileOutputStream(path);
				fl.setLastModified(System.currentTimeMillis());
			} else {
				os = new ParcelFileDescriptor.AutoCloseOutputStream(cr.openFileDescriptor(uri, "w"));
			}

			// copy file
			byte[] dat = new byte[1024];
			int i = is.read(dat);
			while (-1 != i) {
				os.write(dat, 0, i);
				i = is.read(dat);
			}

			is.close();
			is = null;

			os.flush();
			os.close();
			os = null;

			return true;

		} catch (Exception ex) {
			LogUtil.e("Exception, ex: " + ex.toString());
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (Exception ex) {
				}
				;
			}
			if (null != os) {
				try {
					os.close();
				} catch (Exception ex) {
				}
				;
			}
		}
		return false;
	}

	/**
	 * 读取文件流 返回字节数组
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public static byte[] readAll(InputStream is) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
		byte[] buf = new byte[1024];
		int c = is.read(buf);
		while (-1 != c) {
			baos.write(buf, 0, c);
			c = is.read(buf);
		}
		baos.flush();
		baos.close();
		return baos.toByteArray();
	}

	/**
	 * 读文件返回字节数组
	 * 
	 * @param ctx
	 * @param uri
	 * @return
	 */
	public static byte[] readFile(Context ctx, Uri uri) {
		if (null == ctx || null == uri) {
			LogUtil.e("Invalid param. ctx: " + ctx + ", uri: " + uri);
			return null;
		}

		InputStream is = null;
		String scheme = uri.getScheme().toLowerCase();
		if (scheme.equals("file")) {
			is = readFile(uri.getPath());
		}

		try {
			is = ctx.getContentResolver().openInputStream(uri);
			if (null == is) {
				return null;
			}

			byte[] bret = readAll(is);
			is.close();
			is = null;

			return bret;
		} catch (FileNotFoundException fne) {
			LogUtil.e("FilNotFoundException, ex: " + fne.toString());
		} catch (Exception ex) {
			LogUtil.e("Exception, ex: " + ex.toString());
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (Exception ex) {
				}
				;
			}
		}
		return null;
	}

	/**
	 * 写字节数组文件
	 * 
	 * @param filePath
	 * @param content
	 * @return
	 */
	public static boolean writeFile(String filePath, byte[] content) {
		if (null == filePath || null == content) {
			LogUtil.e("Invalid param. filePath: " + filePath + ", content: " + content);
			return false;
		}

		FileOutputStream fos = null;
		try {
			String pth = filePath.substring(0, filePath.lastIndexOf("/"));
			File pf = null;
			pf = new File(pth);
			if (pf.exists() && !pf.isDirectory()) {
				pf.delete();
			}
			pf = new File(filePath);
			if (pf.exists()) {
				if (pf.isDirectory())
					FileHelper.deleteDirectory(filePath);
				else
					pf.delete();
			}

			pf = new File(pth + File.separator);
			if (!pf.exists()) {
				if (!pf.mkdirs()) {
					LogUtil.e("Can't make dirs, path=" + pth);
				}
			}

			fos = new FileOutputStream(filePath);
			fos.write(content);
			fos.flush();
			fos.close();
			fos = null;
			pf.setLastModified(System.currentTimeMillis());

			return true;

		} catch (Exception ex) {
			LogUtil.e("Exception, ex: " + ex.toString());
		} finally {
			if (null != fos) {
				try {
					fos.close();
				} catch (Exception ex) {
				}
				;
			}
		}
		return false;
	}

	/**
	 * 解压zip 返回操作结果
	 */
	/************* ZIP file operation ***************/
	public static boolean readZipFile(String zipFileName, StringBuffer crc) {
		try {
			ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFileName));
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				long size = entry.getSize();
				crc.append(entry.getCrc() + ", size: " + size);
			}
			zis.close();
		} catch (Exception ex) {
			LogUtil.e("Exception: " + ex.toString());
			return false;
		}
		return true;
	}

	/**
	 * 解药zip 返回字节数组
	 * 
	 * @param zipFileName
	 * @return
	 */
	public static byte[] readGZipFile(String zipFileName) {
		if (fileIsExist(zipFileName)) {
			LogUtil.i("zipFileName: " + zipFileName);
			try {
				FileInputStream fin = new FileInputStream(zipFileName);
				int size;
				byte[] buffer = new byte[1024];
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				while ((size = fin.read(buffer, 0, buffer.length)) != -1) {
					baos.write(buffer, 0, size);
				}
				return baos.toByteArray();
			} catch (Exception ex) {
				LogUtil.i("read zipRecorder file error");
			}
		}
		return null;
	}

	public static boolean zipFile(String baseDirName, String fileName, String targerFileName) throws IOException {
		if (baseDirName == null || "".equals(baseDirName)) {
			return false;
		}
		File baseDir = new File(baseDirName);
		if (!baseDir.exists() || !baseDir.isDirectory()) {
			return false;
		}

		String baseDirPath = baseDir.getAbsolutePath();
		File targerFile = new File(targerFileName);
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(targerFile));
		File file = new File(baseDir, fileName);

		boolean zipResult = false;
		if (file.isFile()) {
			zipResult = fileToZip(baseDirPath, file, out);
		} else {
			zipResult = dirToZip(baseDirPath, file, out);
		}
		out.close();
		return zipResult;
	}

	/**
	 * 解压zip文件
	 * 
	 * @param fileName
	 *            文件名
	 * @param unZipDir
	 *            解压路径
	 * @return
	 * @throws Exception
	 */
	public static boolean unZipFile(String fileName, String unZipDir) throws Exception {
		File f = new File(unZipDir);

		if (!f.exists()) {
			f.mkdirs();
		}

		BufferedInputStream is = null;
		ZipEntry entry;
		ZipFile zipfile = new ZipFile(fileName);
		Enumeration<?> enumeration = zipfile.entries();
		byte data[] = new byte[FILE_BUFFER_SIZE];
		LogUtil.i("unZipDir: " + unZipDir);

		while (enumeration.hasMoreElements()) {
			entry = (ZipEntry) enumeration.nextElement();

			if (entry.isDirectory()) {
				File f1 = new File(unZipDir + "/" + entry.getName());
				LogUtil.i("entry.isDirectory XXX " + f1.getPath());
				if (!f1.exists()) {
					f1.mkdirs();
				}
			} else {
				is = new BufferedInputStream(zipfile.getInputStream(entry));
				int count;
				String name = unZipDir + "/" + entry.getName();
				RandomAccessFile m_randFile = null;
				File file = new File(name);
				if (file.exists()) {
					file.delete();
				}

				file.createNewFile();
				m_randFile = new RandomAccessFile(file, "rw");
				int begin = 0;

				while ((count = is.read(data, 0, FILE_BUFFER_SIZE)) != -1) {
					try {
						m_randFile.seek(begin);
					} catch (Exception ex) {
						LogUtil.e("exception, ex: " + ex.toString());
					}

					m_randFile.write(data, 0, count);
					begin = begin + count;
				}

				file.delete();
				m_randFile.close();
				is.close();
			}
		}

		return true;
	}

	/**
	 * 压缩成zip
	 * 
	 * @param baseDirPath
	 *            压缩路径
	 * @param file
	 *            文件
	 * @param out
	 *            zip输入流
	 * @return
	 * @throws IOException
	 */
	private static boolean fileToZip(String baseDirPath, File file, ZipOutputStream out) throws IOException {
		FileInputStream in = null;
		ZipEntry entry = null;

		byte[] buffer = new byte[FILE_BUFFER_SIZE];
		int bytes_read;
		try {
			in = new FileInputStream(file);
			entry = new ZipEntry(getEntryName(baseDirPath, file));
			out.putNextEntry(entry);

			while ((bytes_read = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytes_read);
			}
			out.closeEntry();
			in.close();
		} catch (IOException e) {
			LogUtil.e("Exception, ex: " + e.toString());
			return false;
		} finally {
			if (out != null) {
				out.closeEntry();
			}

			if (in != null) {
				in.close();
			}
		}
		return true;
	}

	private static boolean dirToZip(String baseDirPath, File dir, ZipOutputStream out) throws IOException {
		if (!dir.isDirectory()) {
			return false;
		}

		File[] files = dir.listFiles();
		if (files.length == 0) {
			ZipEntry entry = new ZipEntry(getEntryName(baseDirPath, dir));

			try {
				out.putNextEntry(entry);
				out.closeEntry();
			} catch (IOException e) {
				LogUtil.e("Exception, ex: " + e.toString());
			}
		}

		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				fileToZip(baseDirPath, files[i], out);
			} else {
				dirToZip(baseDirPath, files[i], out);
			}
		}
		return true;
	}

	private static String getEntryName(String baseDirPath, File file) {
		if (!baseDirPath.endsWith(File.separator)) {
			baseDirPath = baseDirPath + File.separator;
		}

		String filePath = file.getAbsolutePath();
		if (file.isDirectory()) {
			filePath = filePath + "/";
		}

		int index = filePath.indexOf(baseDirPath);
		return filePath.substring(index + baseDirPath.length());
	}

	public static long getFolderSize(File file) {
		if (file != null && file.exists()) {
			File[] files = file.listFiles();
			if (files == null){
				return 0;
			}
			long size = 0;
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					size = size + getFolderSize(files[i]);
				} else {
					size = size + files[i].length();

				}
			}
			return size;
		} else {
			return 0;
		}
	}
	/** 
     * 格式化单位 
     * @param size 
     * @return 
     */  
    public static String getFormatSize(double size) {  
        double kiloByte = size/1024;  
        if(kiloByte < 1) {  
            return size + "KB";
        }  
          
        double megaByte = kiloByte/1024;  
        if(megaByte < 1) {  
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));  
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";  
        }  
          
        double gigaByte = megaByte/1024;  
        if(gigaByte < 1) {  
            BigDecimal result2  = new BigDecimal(Double.toString(megaByte));  
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";  
        }  
          
        double teraBytes = gigaByte/1024;  
        if(teraBytes < 1) {  
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));  
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";  
        }  
        BigDecimal result4 = new BigDecimal(teraBytes);  
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";  
    }  
}