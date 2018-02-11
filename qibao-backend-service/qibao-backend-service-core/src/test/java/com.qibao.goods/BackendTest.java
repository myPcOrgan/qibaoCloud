package com.qibao.goods;

import com.qibao.backend.BackendServer;
import com.qibao.backend.entity.FunctionEO;
import com.qibao.backend.entity.UserEO;
import com.qibao.backend.model.BackendRequest;
import com.qibao.backend.redis.IBackendRedisDao;
import com.qibao.backend.service.IBackendService;
import com.qibao.common.utils.BeanMapper;
import com.qibao.common.utils.PassWordHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by 周黎钢 on 2018/1/10.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendServer.class)
public class BackendTest {
    @Autowired
    private IBackendService backendService;
    @Autowired
    private IBackendRedisDao backendRedisDao;
    @Test
    public void testSelectWithRole() {
        List<UserEO>userEOs= backendService.findBackUsers(new BackendRequest());
        for(UserEO userEO:userEOs){
            System.out.println(userEO);
        }
    }
    @Test
    public void testGeneratePass() {
        System.out.println(PassWordHelper.encyptPassword("zhouligang@5173.com","zhouligang@5173.com"));
        System.out.println(new Timestamp(System.currentTimeMillis()));
        System.out.println(new Date());
        BackendRequest request=new BackendRequest();
        request.setId(111L);
        Map<String,String> map=new HashMap<>();
        map=BeanMapper.map(request,Map.class);
        for(Map.Entry entry:map.entrySet()){
            System.out.println("map:"+entry.getKey()+":"+entry.getValue());
        }
        UserEO userEO = new UserEO();
        BeanMapper.copyPropertiesIgnoreNull(request,userEO);
        System.out.println(userEO);
    }
    @Test
    public void testGetBackendFunctions() {
        List<FunctionEO> functionEOs;
        BackendRequest request=new BackendRequest();
        functionEOs=backendService.findBackFunctions(request);
        backendRedisDao.setBackendFunctions(functionEOs);
        List<FunctionEO> functionEOs1=backendRedisDao.getBackendFunctions();
        for (FunctionEO functionEO:functionEOs1){
            System.out.println(functionEO);
        }
    }
    @Test
    public void testBeanMapper() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        Class c=Class.forName("com.qibao.backend.entity.FunctionEO");
//        Object o=c.newInstance();
//        Method method=c.getMethod("setParentId",Long.class);
//        method.invoke(o,10L);
//        Method method1=c.getMethod("getParentId");
//        System.out.println(method1.invoke(o));
//        BeanWrapper beanWrapper=new BeanWrapperImpl(o);
//        beanWrapper.setPropertyValue("parentId",8L);
//        PropertyDescriptor descriptor=beanWrapper.getPropertyDescriptor("parentId");
//        System.out.println(beanWrapper.getPropertyValue(descriptor.getName()));
//        System.out.println(beanWrapper.getPropertyValue("parentId"));
//        System.out.println(((FunctionEO)o).getParentId());
        Long start=System.currentTimeMillis();
        List<FunctionEO> functionEOs=new LinkedList<>();
        BackendRequest request=new BackendRequest();
        request.setParentId(8L);
        FunctionEO functionEO=new FunctionEO();
        functionEO.setId(1L);
        functionEO.setParentId(9L);
        FunctionEO functionEO1=new FunctionEO();
        functionEO1.setId(2L);
        functionEO1.setParentId(9L);
        FunctionEO functionEO2=new FunctionEO();
        functionEO2.setId(3L);
        functionEO2.setParentId(8L);
        functionEOs.add(functionEO);
        functionEOs.add(functionEO1);
        functionEOs.add(functionEO2);
        List<FunctionEO> result = new LinkedList<>();
        for (FunctionEO functionEOo : functionEOs) {
            BeanMapper.getSamePropertyValuesToList(request, functionEOo, result);
        }
        for(FunctionEO functionEO32:result){
            System.out.println("func:"+functionEO32);
        }
        System.out.println("time:"+(System.currentTimeMillis()-start));
    }
    @Test
    public void testTool(){
        String frame = "<frame src=\"test.html\"/>";
        int start = frame.indexOf("src=");
        frame = frame.substring(start);
        int end = frame.indexOf(" ");
        if (end == -1)
            end = frame.indexOf(">");
        String frameUrl = frame.substring(5, end - 2);
        System.out.println(frameUrl);
    }
}
