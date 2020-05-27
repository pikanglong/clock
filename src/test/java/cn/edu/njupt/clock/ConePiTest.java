package cn.edu.njupt.clock;

import cn.edu.njupt.clock.entity.AlarmEntity;
import cn.edu.njupt.clock.utils.MP3Player;
import cn.edu.njupt.clock.utils.ObjectStreamUtils;
import org.junit.Test;

import java.io.File;
import java.util.*;

public class ConePiTest {

    @Test
    public void testObjectStreamUtils() {
        AlarmEntity ae1 = new AlarmEntity(2, 23, true);
        AlarmEntity ae2 = new AlarmEntity(3, 29, false);
        AlarmEntity ae3 = new AlarmEntity(5, 31, true);
        AlarmEntity ae4 = new AlarmEntity(7, 37, false);
        AlarmEntity ae5 = new AlarmEntity(11, 41, true);
        AlarmEntity ae6 = new AlarmEntity(13, 43, false);
        AlarmEntity ae7 = new AlarmEntity(17, 47, true);
        AlarmEntity ae8 = new AlarmEntity(19, 53, false);

        List<AlarmEntity> list = new ArrayList<>();
        list.add(ae4);
        list.add(ae3);
        list.add(ae7);
        list.add(ae5);
        list.add(ae8);
        list.add(ae2);
        list.add(ae1);
        list.add(ae6);

        ObjectStreamUtils.outputObjectList(AlarmEntity.class, list);
        List<AlarmEntity> result = null;
        result = ObjectStreamUtils.inputObjectList(AlarmEntity.class);
        Collections.sort(result);
        Iterator it = result.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void testMP3Player() {
        File file = new File("src/main/resources/sound/watch.mp3");
        System.out.println(file.getAbsolutePath());
        MP3Player mp3Player = new MP3Player(file.getAbsolutePath());
        mp3Player.start();
        Scanner scanner = new Scanner(System.in);
        int off = scanner.nextInt();
        if(off == 0) {
            mp3Player.stop();
        }
    }
}
