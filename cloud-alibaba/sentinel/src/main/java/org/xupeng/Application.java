package org.xupeng;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xupeng
 * @date 2020/12/3 13:54
 * @description
 */
@SpringBootApplication
@RestController
public class Application {


    private static final String KEY = "querySentinel";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @GetMapping("/get")
    public String get() {
        return "OPS正常，没有限流";
    }



    /**
     * 初始化限流配置
     */
    @PostConstruct
    public void initFlowQpsRule() {
        List<FlowRule> rules = new ArrayList<FlowRule>();
        FlowRule rule = new FlowRule();
        rule.setResource(KEY);
        // QPS控制在2以内
        rule.setCount(2);
        // QPS限流
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("default");
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }



    /**
     *  模拟使用Sentinel注解实现限流
     *
     * @param id
     * @return
     */
    @SentinelResource(value = "querySentinel", blockHandler = "handleFlowQpsException",
            fallback = "queryFallback")
    @GetMapping("/query")
    public String query(String id) {

        // 模拟接口运行时抛出代码异常
        if ("000".equals(id)) {
            throw new RuntimeException();
        }
        return "return info :" + id;
    }

    /**
     * 抛出限流或降级时的处理逻辑
     *
     * 注意: 方法参数、返回值要与原函数保持一致
     * @return
     */
    public String handleFlowQpsException(String id, BlockException e) {
        e.printStackTrace();
        return "handleFlowQpsException for 限流或降级的处理逻辑: " + id;
    }

    /**
     * 订单查询接口运行时抛出的异常提供fallback处理
     *
     * 注意: 方法参数、返回值要与原函数保持一致
     * @return
     */
    public String queryOrderInfo2Fallback(String id, Throwable e) {
        return "fallback query 接口异常的应急处理: " + id;
    }
}