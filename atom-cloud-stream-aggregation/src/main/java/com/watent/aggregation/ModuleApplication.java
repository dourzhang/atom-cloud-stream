package com.watent.aggregation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.aggregate.AggregateApplicationBuilder;

@SpringBootApplication
public class ModuleApplication {

	public static void main(String[] args) {
        new AggregateApplicationBuilder()
			.from(SourceApplication.class).args("--fixedDelay=5000")
			.via(ProcessorApplication.class)
			.to(SinkApplication.class).args("--debug=true").run(args);
	}
}