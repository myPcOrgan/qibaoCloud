package com.qibao;

import com.qibao.payment.job.GoldBalanceJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QibaoPaymentServiceApplicationTests {

	@Autowired
	private GoldBalanceJob goldBalanceJob;

	@Test
	public void contextLoads() {
		goldBalanceJob.add();
	}

}
