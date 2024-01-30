package insurenceMain.Services;

import java.util.List;
import java.util.Map;

import insurenceMain.Entity.CreatePlan;

public interface CreatePlanServices {
	
	public Map<Integer, String>getAllCategories();
	
	public Integer savePlan(CreatePlan plan);
	
	public List<CreatePlan>viewPlans();
	
	public Boolean updatePlan(Integer id, CreatePlan plan);
	
	public Boolean deletePlan(Integer id);
	
	public Boolean activeStatus(Integer id,String flag);

}
