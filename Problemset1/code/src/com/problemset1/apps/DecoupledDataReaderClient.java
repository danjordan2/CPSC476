package com.problemset1.apps;
import com.problemset1.readers.IReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DecoupledDataReaderClient {
  private IReader reader = null;
  private ApplicationContext ctx = null;

  public DecoupledDataReaderClient() {
    ctx = new ClassPathXmlApplicationContext("../beans.xml");
  }

  private String fetchData() {
    reader = (IReader) ctx.getBean("reader");
    return reader.read();
  }

  public static void main(String[] args) {
    DecoupledDataReaderClient client = new DecoupledDataReaderClient();
    System.out.println("Using Decoupled Data Client, Got data: " + client.fetchData());
  }
}