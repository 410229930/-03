//读取图片  通过JAVA自带的ImageIO里面的read方法
BufferedImage bufImage = ImageIO.read(File input);
BufferedImage bufImage = ImageIO.read(URL input);
BufferedImage bufImage = ImageIO.read(InputStream input);
BufferedImage bufImage = ImageIO.read(ImageInputStream input)


/**
 *  image:RenderedImage 接口的实现类, BufferedImage 实现了 RenderedImage 接口
 *  formatName: 保存的图片格式的名称
 *  output: 结果输出位置
 */
ImageIO.write(RenderedImage image, String formatName, File output);
ImageIO.write(RenderedImage image, String formatName, OutputStream output);


Image getScaledInstance(int width, int height, int hints);


// 创建图片的画布
Graphics2D createGraphics();
 
// 获取图片的画布, 此方法实际上内部调用了 createGraphics() 方法, Graphics2D 继承自 Graphics
Graphics getGraphics();
 
/*
 * 获取到 Graphics2D 对象后, 调用它的绘图方法，便可在原图片上绘制几何图形、文字、图片（贴图）等。
 * 获取到的画布是新创建的, 使用完后需要调用 Graphics 的 dispose() 方法销毁。
 */
 
 
 
 
 import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
 
 
public class TestImg {
 
    public static void cutUrl(String imageUrl, int width, int height) throws Exception {
        URL url = new URL(imageUrl);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        /*设置请求方式为"GET"/
        httpConn.setRequestMethod("GET");
        /*超时响应时间为5秒/
        httpConn.setConnectTimeout(5 * 1000);
        httpConn.connect();
        InputStream is = httpConn.getInputStream();
        // 读取图片
        BufferedImage bufImage = ImageIO.read(is);
        BufferedImage tag=new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        //缩放图片
        tag.getGraphics().drawImage(bufImage, 0, 0, width, height, null);
        File imgFile = new File("bb.jpg");
        ImageIO.write(tag, "JPEG", imgFile);
        /**
         * 裁剪图片
         * @param x   起始x坐标
         * @param y   起始y坐标
         * @param w  要裁剪的图片的宽度
         * @param h  要裁剪的图片的高度
         */
        BufferedImage bufferedImage = bufImage.getSubimage(0,0,300,300);
        File imgCutFile = new File("bb2.jpg");
        ImageIO.write(bufferedImage, "JPEG", imgCutFile);
 
       //缩放图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        Image img = bufImage.getScaledInstance(width , height, Image.SCALE_DEFAULT);
        graphics.drawImage(img, 0, 0,null);
        //一定要释放资源
        graphics.dispose();
        File imgZoomFile = new File("bb3.jpg");
        ImageIO.write(image, "JPEG", imgZoomFile);
 
 
    }
 
    public static void main(String[] args) {
        try {
            TestImg.cutUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1589025741669&di=147c6c43977670ff0ac906d89f2e0584&imgtype=0&src=http%3A%2F%2Fimg2.i