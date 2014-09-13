import java.io.*;
import java.nio.channels.FileChannel;
public class CopyEverythingOnPendriveInsert {
	private static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
	private static void copyFileUsingChannel(File source, File dest) throws IOException {
		FileChannel SChannel=null;
		FileChannel DChannel=null;
		try{
			SChannel=new FileInputStream(source).getChannel();
			DChannel=new FileOutputStream(dest).getChannel();
			SChannel.transferTo(0, SChannel.size(), DChannel);
		}finally{
			if(SChannel!=null) SChannel.close();
			if(DChannel!=null) DChannel.close();
		}
	}
	public static void main(String[] args){
		boolean flag=false;
		String username=System.getProperty("user.name");
		String source="",dest="C:/Users/"+username+"/Desktop/Temporary";
		File[] roots=File.listRoots();
		for(int i=roots.length-1;i>=0;i--){
			if(roots[i].toString().charAt(0)=='I'||roots[i].toString().charAt(0)=='F'||
					roots[i].toString().charAt(0)=='H'||roots[i].toString().charAt(0)=='G'
					||roots[i].toString().charAt(0)=='E')
				flag=true;
			else	flag=false;
			if(flag){
				if(roots[i].exists()){
					source=roots[i].toString();
					break;
				}
			}
			
		}
		File sourceDir=new File(source);
		File destDir=new File(dest);
		if(!destDir.exists()){
			destDir.mkdir();
		}
		if(sourceDir.isDirectory()){
			/*
			String[] arr=sourceDir.list();
			for(int i=0;i<arr.length;i++){
				File f1=new File(source+"/"+arr[i]);
				System.out.println(f1.getPath());
				if(f1.isFile()){
					File f2=new File(dest+"/"+arr[i]);
						try {
							copyFileUsingChannel(f1,f2);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
			*/
			try {
				copyEverything(sourceDir,destDir);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println(source+" is not a directory");
		}
	}
	private static void copyEverything(File sourceDir, File destDir) throws IOException{
		// TODO Auto-generated method stub
		if(sourceDir.isDirectory()){
			if(!destDir.exists()){
				destDir.mkdir();
			}
			String[] files=sourceDir.list();
			for(String file:files){
				File srcFile=new File(sourceDir,file);
				File destFile=new File(destDir,file);
				copyEverything(srcFile,destFile);
			}
		}else{
			copyFileUsingChannel(sourceDir,destDir);
		}
	}
}
