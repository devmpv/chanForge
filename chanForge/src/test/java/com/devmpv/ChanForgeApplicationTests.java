package com.devmpv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.devmpv.ChanForge;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChanForge.class)
@WebAppConfiguration
public class ChanForgeApplicationTests {

	@Test
	public void contextLoads() {
	}

}
