package effect;

import java.util.Hashtable;

public class CacheDataLoader {

    private static CacheDataLoader instance = null;
    private Hashtable<String, FrameImage> frameImages;
    private  Hashtable<String, Animation> animations;
    private CacheDataLoader() {
        frameImages = new Hashtable<String, FrameImage>();
        animations = new Hashtable<String, Animation>();
    }

    public static CacheDataLoader getInstance() {
        if(instance == null)
            instance = new CacheDataLoader();
        return instance;
    }

    public void LoadFrame() throws IOException{

        frameImages = new Hashtable<String, FrameImage>();

        FileReader fr = new FileReader(framefile);
        BufferedReader br = new BufferedReader(fr);

        String line = null;

        if(br.readLine()==null) {
            System.out.println("No data");
            throw new IOException();
        }
        else {

            fr = new FileReader(framefile);
            br = new BufferedReader(fr);

            while((line = br.readLine()).equals(""));

            int n = Integer.parseInt(line);
            String path = null;
            BufferedImage imageData = null;
            int i2 = 0;
            for(int i = 0;i < n; i ++){

                FrameImage frame = new FrameImage();
                while((line = br.readLine()).equals(""));
                frame.setName(line);

                while((line = br.readLine()).equals(""));
                String[] str = line.split(" ");

                boolean refreshImage = (path == null || !path.equals(str[1]));
                path = str[1];

                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int x = Integer.parseInt(str[1]);

                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int y = Integer.parseInt(str[1]);

                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int w = Integer.parseInt(str[1]);

                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int h = Integer.parseInt(str[1]);

                if(refreshImage) {
                    refreshImage = false;
                    imageData = ImageIO.read(new File(path));
                }
                if(imageData != null) {
                    BufferedImage image = imageData.getSubimage(x, y, w, h);
                    frame.setImage(image);
                }

                instance.frameImages.put(frame.getName(), frame);
            }

        }

        br.close();

    }
}
