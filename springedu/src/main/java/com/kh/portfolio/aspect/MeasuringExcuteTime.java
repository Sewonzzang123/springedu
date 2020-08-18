package com.kh.portfolio.aspect;

import java.sql.SQLException;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Component // 컨테이너에 bean으로 등록하고자 하는 클래스에 사용
//@Aspect // 부가기능을 정의한 클래스에 사용
public class MeasuringExcuteTime {
	private static final Logger logger = LoggerFactory.getLogger(MeasuringExcuteTime.class);

	//around: 타겟의 메서드가 호출되기 이전, 이후시점에 모두 처리해야할필요가있는 부가기능 before,after returning, after throwing...
	// .(이하의 모든 패키지).*(모든 클래스).*(모든 메소드)(..)(에 대해서 적용하겠다)
	//joinPoint:본래 수행해야하는 메소드
	//getSignature(): 시그니처(리턴타입, 이름, 매개변수) 정보가 저장된 Signature 객체 리턴
	@Around("execution(* com.kh.portfolio..*.*(..))")
	public Object measuringMethodRoundingTime(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;
		Signature signature = joinPoint.getSignature();
		StringBuilder methodName = new StringBuilder();
		methodName.append(signature.getDeclaringTypeName())
		.append(".")
		.append(signature.getName());
		

		logger.info("[Log: Around] Before: " + methodName + " start time: "
									+Arrays.toString(joinPoint.getArgs()));

		long startTime = System.nanoTime();
		try {
			// 핵심기능 수행( result : object type)
			result = joinPoint.proceed();
		} catch (Throwable e) {
			logger.info("[Log: Around] Exception :" + methodName);
				throw e;
		} finally {
			logger.info("[Log: Around] finally:" + methodName);
			// result가 null이면 핵심기능이 정상적으로 수행됨
			if (result != null) {
				logger.info("[Log: Around] result: " + result.toString());
			}
		}
		long endTime = System.nanoTime();
		double takenTime = (endTime-startTime)/(double)1000000000;
		logger.info("[Log: Around] After: " + methodName + " end");
		logger.info("[Log: Around]" + methodName + " Processing time: " + takenTime + "sec");

// 시간 초로 구하기
//		long takenTime = TimeUnit.NANOSECONDS.toSeconds(endTime-startTime);
//		logger.info("[Log: Around] After: " + methodName + " end");
//		logger.info("[Log: Around]" + methodName + " Processing time: " + takenTime + "sec");

		return result;
	}
}
