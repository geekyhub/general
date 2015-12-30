package com.geekylab.general.network.api;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Environment;

import com.geekylab.general.utils.UnzipUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by User on 16/11/15.
 */
public class ZipDownloader {

        private String url="https://s3.amazonaws.com/fthtml5/Archive.zip";
        private String storezipFileLocation = "file:///android_asset";//Environment.getExternalStorageDirectory() + "/DownloadedZip";
        private String directoryName="file:///android_asset/hi";//Environment.getExternalStorageDirectory() + "/unzipFolder/files/";


        public ZipDownloader(String url, String location, String dirName) {
            this.url = url;
            this.storezipFileLocation = location;
            this.directoryName = location;
            DownloadZipfile mew = new DownloadZipfile();
            mew.execute(url);
        }

        class DownloadZipfile extends AsyncTask<String, String, String> {
            String result ="";
            @Override
            protected String doInBackground(String... aurl) {
                int count;
                try
                {
                    System.out.println("lucy download");
                    URL url = new URL(aurl[0]);
                    System.out.println("lucy download1");
                    URLConnection conexion = url.openConnection();
                    System.out.println("lucy download2");
                    conexion.connect();
                    int lenghtOfFile = conexion.getContentLength();
                    System.out.println("lucy download3");
                    InputStream input = new BufferedInputStream(url.openStream());
                    OutputStream output = new FileOutputStream(storezipFileLocation);
                    System.out.println("lucy download4");
                    byte data[] = new byte[1024];
                    long total = 0;
                    System.out.println("lucy download5");
                    while ((count = input.read(data)) != -1) {
                        total += count;
                        publishProgress(""+(int)((total*100)/lenghtOfFile));
                        output.write(data, 0, count);
                    }
                    System.out.println("lucy download6");
                    output.close();
                    input.close();
                    result = "true";
                    System.out.println("lucy download done");

                } catch (Exception e) {
                    e.printStackTrace();
                    result = "false";
                }
                return null;

            }


            @Override
            protected void onPostExecute(String unused) {
                if(result.equalsIgnoreCase("true")) {
                    try {
                        System.out.println("lucy download unzip");
                        unzip();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                else {
                }
            }
        }

        public void unzip() throws IOException {
            new UnZipTask().execute(storezipFileLocation, directoryName);
        }

        private class UnZipTask extends AsyncTask<String, Void, Boolean> {
            @SuppressWarnings("rawtypes")
            @Override
            protected Boolean doInBackground(String... params)
            {
                System.out.println("lucy download UnZipTask");
                String filePath = params[0];
                String destinationPath = params[1];

                File archive = new File(filePath);
                try
                {
                    ZipFile zipfile = new ZipFile(archive);
                    for (Enumeration e = zipfile.entries(); e.hasMoreElements();)
                    {
                        ZipEntry entry = (ZipEntry) e.nextElement();
                        unzipEntry(zipfile, entry, destinationPath);
                    }

                    System.out.println("lucy download UnZipTask util");
                    UnzipUtil d = new UnzipUtil(storezipFileLocation, directoryName);
                    d.unzip();
                    System.out.println("lucy download UnZipTask util done");

                }
                catch (Exception e)
                {
                    return false;
                }

                return true;
            }




            private void unzipEntry(ZipFile zipfile, ZipEntry entry,String outputDir) throws IOException {
                if (entry.isDirectory()) {
                    createDir(new File(outputDir, entry.getName()));
                    return;
                }

                File outputFile = new File(outputDir, entry.getName());
                if (!outputFile.getParentFile().exists())
                {
                    createDir(outputFile.getParentFile());
                }

                // Log.v("", "Extracting: " + entry);
                BufferedInputStream inputStream = new BufferedInputStream(zipfile.getInputStream(entry));
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));

                try
                {

                }
                finally
                {
                    outputStream.flush();
                    outputStream.close();
                    inputStream.close();
                }
            }

            private void createDir(File dir)
            {
                if (dir.exists())
                {
                    return;
                }
                if (!dir.mkdirs())
                {
                    throw new RuntimeException("Can not create dir " + dir);
                }
            }
        }
    }





