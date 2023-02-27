package djadi.rabah.recipes.select;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.OptionGroupModel;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.util.AbstractSelectModel;

import djadi.rabah.recipes.pojo.Measure;

public class MeasureIdSelectModel extends AbstractSelectModel 
{
	private List<Measure> measures;

    public MeasureIdSelectModel(List<Measure> measures) {
    	this.measures = measures;
    }
    
    @Override
    public List<OptionGroupModel> getOptionGroups() {
        return null;
    }

    @Override
    public List<OptionModel> getOptions() {
        List<OptionModel> options = new ArrayList<OptionModel>();
        for (Measure measure : measures) {
        	if(measure.getMeasure() == null)
        		options.add(new OptionModelImpl("", measure.getId()));
        	else
        		options.add(new OptionModelImpl(measure.getMeasure(), measure.getId()));
        }
        return options;
    }
}
