package insurenceMain.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import insurenceMain.Entity.CreatePlan;
import insurenceMain.Entity.PlanCategory;
import insurenceMain.Repository.CreatePlanRepository;
import insurenceMain.Repository.PlanCategoryRepository;

@Service
public class CreatePlanServicesImpl implements CreatePlanServices{

	@Autowired
	private CreatePlanRepository planRepo;

	@Autowired
	private PlanCategoryRepository categoryRepo;

	@Override
	public Map<Integer, String> getAllCategories() {

		List<PlanCategory> findAll = categoryRepo.findAll();
		Map<Integer, String> catMap = new HashMap<>();

		findAll.forEach(c -> catMap.put(c.getPlanCategoryId(), c.getCategoryName()));
		return catMap;
	}

	@Override
	public Integer savePlan(CreatePlan plan) {

		CreatePlan save = planRepo.save(plan);

		return save.getPlanId();
	}

	@Override
	public List<CreatePlan> viewPlans() {

		List<CreatePlan> all = planRepo.findAll();
		return all;
	}

	@Override
	public Boolean updatePlan(Integer id, CreatePlan plan) {
		Optional<CreatePlan> findById = planRepo.findById(id);
		if(findById.isPresent()) {
			CreatePlan inPlan = findById.get();
			inPlan.setPlanName(plan.getPlanName());
			inPlan.setPlanStartDate(plan.getPlanStartDate());
			inPlan.setPlanEndDate(plan.getPlanEndDate());
			planRepo.save(inPlan);
			return true;
		}

		return false;
	}

	@Override
	public Boolean deletePlan(Integer id) {
		boolean status = false;

		try {
			planRepo.findById(id);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return status;

	}

	@Override
	public Boolean activeStatus(Integer id, String flag) {

		Optional<CreatePlan> id2 = planRepo.findById(id);
		if(id2.isPresent()) {
			CreatePlan createPlan = id2.get();
			createPlan.setActiveStatus(flag);
			planRepo.save(createPlan);
			return true;
		}

		return false;
	}




}
