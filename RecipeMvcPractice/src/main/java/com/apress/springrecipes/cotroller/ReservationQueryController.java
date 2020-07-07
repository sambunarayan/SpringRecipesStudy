package com.apress.springrecipes.cotroller;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.apress.springrecipes.domain.Reservation;
import com.apress.springrecipes.service.ReservationService;
import com.apress.springrecipes.utils.Delayer;

@Controller
@RequestMapping("/reservationQuery")
public class ReservationQueryController {
	
	private Log log = LogFactory.getLog(this.getClass());

	private final ReservationService reservationService;
	
	private final TaskExecutor taskExecutor;
	
	private final AsyncListenableTaskExecutor listenableTaskExecutor;
	
	public ReservationQueryController(ReservationService reservationService, TaskExecutor taskExecutor, AsyncListenableTaskExecutor listenableTaskExecutor) {
		this.reservationService = reservationService;
		this.taskExecutor = taskExecutor;
		this.listenableTaskExecutor = listenableTaskExecutor;
	}
	
	@GetMapping
	public void setupForm() {}
	
	@PostMapping("/callable")
	public Callable<String> submitForm(@RequestParam("courtName") String courtName, Model model) {
		return ()->{
			log.info("submitForm --> " + courtName);
			List<Reservation> reservations = Collections.emptyList();
			if (courtName != null) {
				Delayer.randomDelay();
				reservations = reservationService.query(courtName);
			}
			model.addAttribute("reservations", reservations);
			return "reservationQuery";
		};
	}
	
	@PostMapping("/deferred")
	public DeferredResult<String> submitFormForDeferredResult(@RequestParam("courtName") String courtName, Model model) {
		
		final DeferredResult<String> result = new DeferredResult<>();
		
		taskExecutor.execute(()->{
			System.out.println("submitFormForDeferredResult --> " + courtName);
			List<Reservation> reservations = Collections.emptyList();
			if (courtName != null) {
				Delayer.randomDelay();
				reservations = reservationService.query(courtName);
			}
			model.addAttribute("reservations", reservations);
			result.setResult("reservationQuery");
		});
				
		return result;
	}
	
	@PostMapping("/completable")
	public CompletableFuture<String> submitFormForCompletableFUtrue(@RequestParam("courtName") String courtName, Model model) {
		return CompletableFuture.supplyAsync(()->{
			System.out.println("submitFormForCompletableFUtrue --> " + courtName);
			List<Reservation> reservations = Collections.emptyList();
			if (courtName != null) {
				Delayer.randomDelay();
				reservations = reservationService.query(courtName);
			}
			model.addAttribute("reservations", reservations);
			return "reservationQuery";
		}, taskExecutor);
	}
	
	@PostMapping("/listenable")
	public ListenableFuture<String> submitFormListenable(@RequestParam("courtName") String courtName, Model model) {
		return listenableTaskExecutor.submitListenable(()->{
			System.out.println("submitFormListenable --> " + courtName);
			List<Reservation> reservations = Collections.emptyList();
			if (courtName != null) {
				Delayer.randomDelay();
				reservations = reservationService.query(courtName);
			}
			model.addAttribute("reservations", reservations);
			return "reservationQuery";
		});
	}
	
	@PostMapping("/emitter")
	public ResponseEntity<ResponseBodyEmitter> find(@RequestParam("courtName") String courtName) {
		ResponseBodyEmitter emitter = new ResponseBodyEmitter();
		System.out.println("emitter execute");
		taskExecutor.execute(()->{
			Collection<Reservation> reservations = reservationService.query(courtName);
			try {
				for (Reservation reservation :reservations) {
					Delayer.randomDelay();
					emitter.send(reservation);
					System.out.println("reservation sent");
				}
				emitter.complete();
			} catch (IOException e) {
				emitter.completeWithError(e);
			}
		});
		System.out.println("ResponseBodyEmitter");
		return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT)
				.header("Custom-Header", "Custom-Value")
				.body(emitter);
	}
	
	@PostMapping("/sseemitter")
	public SseEmitter findBySseEmitter(@RequestParam("courtName") String courtName) {
		SseEmitter emitter = new SseEmitter();
		taskExecutor.execute(()->{
			Collection<Reservation> reservations = reservationService.query(courtName);
			try {
				for (Reservation reservation : reservations) {
					Delayer.delay(125);
					emitter.send(emitter.event().id(String.valueOf(reservation.hashCode())).data(reservation));
				}
				emitter.complete();
			} catch (IOException e) {
				emitter.completeWithError(e);
			}
		});
		return emitter;
	}
}
