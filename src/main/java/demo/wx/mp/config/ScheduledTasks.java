package demo.wx.mp.config;

import demo.wx.mp.pojo.MsgVO;
import demo.wx.mp.service.MsgVOService;
import demo.wx.mp.service.VxPushMsgService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Desc 定时任务, 基于注解@Scheduled
 */
@Slf4j
@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {
    @Autowired
    private MsgVOService msgVOService;

    @Autowired
    private VxPushMsgService vxPushMsgService;

    /**
     * 每天8点执行
     */
    @Scheduled(cron = "0 0 8 * * ?")
    @Transactional
    public void reportCurrentTime1() throws ExecutionException, InterruptedException {
        //构造一个线程池
       ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                20,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(200));
        //查询出所有会员用户
        List<MsgVO> userList = msgVOService.queryAll();
        //开始时间
        long startTime = System.currentTimeMillis();

        Future<Float> submit = executor.submit(() -> {
            //执行线程任务
            for (MsgVO user : userList) {
                if (user.isDisable()){
                    // 在这里可以调用Service层中需要完后定时任务的方法
                    vxPushMsgService.pushRenewalNotice(user);
                }
            }
            long endTime = System.currentTimeMillis();
            return (endTime - startTime) / 1000f;
        });
        executor.shutdown();
        log.info("执行时间为："+submit.get().toString()+"s");
    }

    @Scheduled(cron = "0 0 20 * * ?")
    @Transactional
    public void reportCurrentTime2() throws ExecutionException, InterruptedException {
        //构造一个线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                20,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(200));
        //查询出所有会员用户
        List<MsgVO> userList = msgVOService.queryAll();
        //开始时间
        long startTime = System.currentTimeMillis();

        Future<Float> submit = executor.submit(() -> {
            //执行线程任务
            for (MsgVO user : userList) {
                if (user.isDisable()){
                    // 在这里可以调用Service层中需要完后定时任务的方法
                    vxPushMsgService.pushRenewalNotice(user);
                }
            }
            long endTime = System.currentTimeMillis();
            return (endTime - startTime) / 1000f;
        });
        executor.shutdown();
        log.info("执行时间为："+submit.get().toString()+"s");
    }
}

