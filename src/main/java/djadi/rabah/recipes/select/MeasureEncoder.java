package djadi.rabah.recipes.select;

import java.util.List;

import org.apache.tapestry5.ValueEncoder;

import djadi.rabah.recipes.pojo.Measure;

public class MeasureEncoder implements ValueEncoder<Measure>
{
	private List<Measure> measures;
	
	public MeasureEncoder(List<Measure> measures) 
	{
		this.measures = measures;
	}

	@Override
	public String toClient(Measure value) {
		return String.valueOf(value.getId());
	}

	@Override
	public Measure toValue(String clientValue) {
		for(Measure measure : measures)
		{
			if(measure.getMeasure().equals(clientValue))
				return measure;
		}
		return null;
	}

}
