package djadi.rabah.recipes.select;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.OptionGroupModel;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.util.AbstractSelectModel;

import djadi.rabah.recipes.pojo.Aliment;

public class AlimentIdSelectModel extends AbstractSelectModel 
{
	private List<Aliment> aliments;

    public AlimentIdSelectModel(List<Aliment> aliments) {
    	this.aliments = aliments;
    }
    
    @Override
    public List<OptionGroupModel> getOptionGroups() {
        return null;
    }

    @Override
    public List<OptionModel> getOptions() {
        List<OptionModel> options = new ArrayList<OptionModel>();
        for (Aliment aliment : aliments) {
            options.add(new OptionModelImpl(aliment.getAliment(), aliment.getId()));
        }
        return options;
    }

}
