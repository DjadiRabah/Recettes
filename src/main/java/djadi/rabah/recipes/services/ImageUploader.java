package djadi.rabah.recipes.services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;

import javax.imageio.ImageIO;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class ImageUploader {
	private static ImageUploader imageUploader;
	private final String CLOUD_NAME = "dafkxhd7n";
	private final String API_KEY = "991226128179868";
	private final String API_SECRET = "0QkglIDRvT7RZbeR651gjo0ADnQ";

	private Cloudinary cloudinary;

	private ImageUploader() {
		this.cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", CLOUD_NAME,
				"api_key", API_KEY,
				"api_secret", API_SECRET));
	}
	
	public static synchronized ImageUploader getInstance()
	{
		if(imageUploader == null)
			imageUploader = new ImageUploader();
		return imageUploader;
	}

	public String upload(String filePath) {
		
		try {
			String extension = filePath.substring(filePath.lastIndexOf(".")+1);
			URL url = new URL(filePath);
			BufferedImage img = ImageIO.read(url);
			File file = new File("image." + extension);
			ImageIO.write(img, extension, file);
			
			Map<?, ?> uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
			return (String) uploadResult.get("url");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getImage(String filePath)
	{
		return "https://res.cloudinary.com/" + CLOUD_NAME + "/image/upload/" + filePath;
	}
	
	public String getImageThumb(String filePath)
	{
		String[] split = filePath.split("/");
		String privatePath = split[0];
		return "https://res.cloudinary.com/" + CLOUD_NAME + "/image/upload/" + privatePath + "/c_thumb,w_200,g_face/" + split[1] + "/" + split[2] + "/" +split[3];
	}
	
	public void destroy(String filePath)
	{
		try {
			String[] split = filePath.split("\\/");
			String folder = split[2];
			String fileName = split[3];
			String file = folder + "/" + fileName.split("\\.")[0];
			cloudinary.api().deleteResources(Arrays.asList(file), ObjectUtils.emptyMap());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
