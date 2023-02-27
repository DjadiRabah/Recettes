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

public class EditInstructionForm extends BasePage {
	@Inject
	private AlertManager alertManager;
	
	@InjectComponent("form")
	private Form form;
	
	@Parameter(required = true)
	@Property
	private Instruction instruction;

	@Parameter(required = true)
	@Property
	private int idCreator;

	public void onValidateFromForm() {
		if (idCreator == getIdUser())
		{
			for(Instruction current : InstructionDao.getInstance().getInstructionsByIdRecipe(instruction.getIdRecipe()))
			{
				if(current.getInstruction().equals(instruction.getInstruction()))
				{
					alertManager.error("L'instruction : " + instruction.getInstruction() + " existe déjà.");
					return;
				}
			}
			InstructionDao.getInstance().update(instruction);
		}
	}

	public void onDeleteInstruction(int idInstruction) {
		if (idCreator == getIdUser())
			InstructionDao.getInstance().deleteById(idInstruction);
	}
}
