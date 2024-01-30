package insurenceMain.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import insurenceMain.AppProperties.AppProperties;
import insurenceMain.Constants.AppConstants;
import insurenceMain.Entity.CreatePlan;
import insurenceMain.Services.CreatePlanServices;

@RestController
public class CreatePlanController {

	private CreatePlanServices createServices;
	
	
	private Map<String, String> messages;
	
	
	
  @Autowired //OPTINAL AUTO WIRE IF WE USE CONTSRUCTER
	public CreatePlanController(CreatePlanServices createServices, AppProperties appPro) {
		super();
		this.createServices = createServices;
		this.messages = appPro.getMessages();
	}

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories(){
		Map<Integer, String> allCategories = createServices.getAllCategories();
		return new ResponseEntity<>(allCategories,HttpStatus.OK);
	}

	@PostMapping("/plan")
	public ResponseEntity<String>createPlan(@RequestBody CreatePlan plan){

		Integer savePlan = createServices.savePlan(plan);
		String rep =AppConstants.EMPTY_STR;
		
		if(savePlan != null) {
			rep=messages.get(AppConstants.PLAN_SAVE_SUCC);
		}else {
			rep =messages.get(AppConstants.PLAN_SAVE_FAIL);
		}
		return new ResponseEntity<>(rep,HttpStatus.CREATED);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<CreatePlan>>viewPlans(){

		List<CreatePlan> viewPlans = createServices.viewPlans();
		return new ResponseEntity<>(viewPlans,HttpStatus.OK);

	}
	@PutMapping("/plan/{id}")
	public ResponseEntity<String>updatePlan(@RequestBody CreatePlan plan,@PathVariable Integer id){

		Boolean plan2 = createServices.updatePlan(id, plan);
		String rep =AppConstants.EMPTY_STR;
		if(plan2) {
			rep =messages.get(AppConstants.PLAN_UPDATE_SUCC);
		}else {
			rep =messages.get(AppConstants.PLAN_UPDATE_FAIL);
		}
		return new ResponseEntity<>(rep,HttpStatus.CREATED);
	}

	@DeleteMapping("/plan{id}")
	public ResponseEntity<String>deletePlan(@PathVariable Integer id){

		Boolean deletePlan = createServices.deletePlan(id);
		String rep =AppConstants.EMPTY_STR;

		if(deletePlan) {
			rep=messages.get(AppConstants.PLAN_DELETE_SUCSS);
		}else {
			rep =messages.get(AppConstants.PLAN_DELETE_FAIL);
		}
		return new ResponseEntity<>(rep,HttpStatus.OK);
	}

	@PutMapping("/plan{id}/{flag}")
	public ResponseEntity<String>ActiveStatus(@PathVariable Integer id,@PathVariable String flag){

		Boolean activeStatus = createServices.activeStatus(id, flag);
		String rep =AppConstants.EMPTY_STR;
		if(activeStatus) {
			rep=messages.get(AppConstants.PLAN_STATUS_CHANGE);
		}else {
			rep =messages.get(AppConstants.PLAN_STATUS_CHANGE_FAIL);
		}
		return new ResponseEntity<>(rep,HttpStatus.OK);
	}


}
