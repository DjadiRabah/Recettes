package djadi.rabah.recipes.components;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import djadi.rabah.recipes.dao.impl.InstructionDao;
import djadi.rabah.recipes.pages.BasePage;
import djadi.rabah.recipes.pojo.Instruction;

public class AddInstructionForm extends BasePage {

	@Inject
	private AlertManager alertManager;

	@InjectComponent("form")
	private Form form;

	@Parameter(required = true)
	@Property
	private int idRecipe;
	
	@Parameter(required = true)
	@Property
	private int idCreator;
	
	@Property
	private Instruction instruction;
	
	public void onPrepare() 
	{
		instruction = new Instruction();
		instruction.setIdRecipe(idRecipe);
		instruction.setRank(InstructionDao.getInstance().getRankMax(idRecipe) + 1);
		
	}

	public void onValidateFromForm() {
		InstructionDao.getInstance().insert(instruction);
	}
}
