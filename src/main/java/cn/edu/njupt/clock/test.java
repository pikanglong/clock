package cn.edu.njupt.clock;

import cn.edu.njupt.clock.entity.AlarmEntity;
import cn.edu.njupt.clock.utils.ObjectStreamUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class test {

    @Test
    public void testObjectStreamUtils() {
        AlarmEntity ae1 = new AlarmEntity(1, 1, true);
        AlarmEntity ae2 = new AlarmEntity(2, 2, false);
        AlarmEntity ae3 = new AlarmEntity(3, 3, true);
        AlarmEntity ae4 = new AlarmEntity(4, 4, false);
        AlarmEntity ae5 = new AlarmEntity(5, 5, true);
        AlarmEntity ae6 = new AlarmEntity(6, 6, false);
        AlarmEntity ae7 = new AlarmEntity(7, 7, true);
        AlarmEntity ae8 = new AlarmEntity(8, 8, false);

        List<AlarmEntity> list = new ArrayList<>();
        list.add(ae1);
        list.add(ae2);
        list.add(ae3);
        list.add(ae4);
        list.add(ae5);
        list.add(ae6);
        list.add(ae7);
        list.add(ae8);

        ObjectStreamUtils.outputObjectList(AlarmEntity.class, list);
        List<AlarmEntity> result = null;
        result = ObjectStreamUtils.inputObjectList(AlarmEntity.class);
        Iterator it = result.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
