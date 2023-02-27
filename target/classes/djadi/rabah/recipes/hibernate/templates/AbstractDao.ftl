${pojo.getPackageDeclaration()}
// Generated ${date} by Hibernate Tools ${version}

<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
import djadi.rabah.recipes.pojo.${declarationName};
import djadi.rabah.recipes.dao.Dao;
import djadi.rabah.recipes.dao.DaoConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.google.common.base.CaseFormat;

/**
 * Auto Dao object for domain model class ${declarationName}.
 * @see ${pojo.getQualifiedDeclarationName()}
 * @author Rabah DJADI
 */
public abstract class Abstract${declarationName}DaoImpl implements Dao<${declarationName}>{

	protected final String TABLE = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "${declarationName}");
	
	@Override
	public void deleteById(int id${declarationName}) 
	{
		try 
		{
			String query = "DELETE FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query);
			statement.setInt(1, id${declarationName});
			statement.executeUpdate();
			statement.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	/**
  	* Update the database's ${declarationName} table
  	* @see Dao#update()
  	*/
	@Override
	public void update(${declarationName} pojo) 
	{
		try 
		{
			<#assign query = "">
			<#foreach property in pojo.getAllPropertiesIterator()>
		    <#if "${pojo.getJavaTypeName(property, jdk5)}"?contains("Set")>
		    <#else>
		    	<#if "${property.name}" != "id">
		    		<#if "${pojo.getJavaTypeName(property, jdk5)}"?contains("Int") || "${pojo.getJavaTypeName(property, jdk5)}"?contains("String")
				   	|| "${pojo.getJavaTypeName(property, jdk5)}"?contains("Boolean") || "${pojo.getJavaTypeName(property, jdk5)}"?contains("Date")>
		    			<#assign query = query + "${property.name} = ?,">
		    		<#else>
		    			<#if "${pojo.getPropertyName(property)}"?contains("By")>
								<#assign query = query + "id" + "${pojo.getPropertyName(property)}"?substring("${pojo.getPropertyName(property)}"?index_of("By") + 4) + " = ?,">
						<#else>
					  		<#assign query = query + "id${pojo.getJavaTypeName(property, jdk5)} = ?,">
				  		</#if>
		    		</#if>
		    	</#if>
		    </#if>
			</#foreach>
			
			<#assign query ="${query}"?substring(0,"${query}"?length-1)>
			String query =  "UPDATE " + TABLE + " SET ${query} WHERE id = ?";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query);    
			<#assign count = 1>
			<#foreach property in pojo.getAllPropertiesIterator()>
				<#if "${property.name}" != "id">
				    <#if "${pojo.getJavaTypeName(property, jdk5)}"?contains("Set")>
				    <#else>
				    	<#if "${pojo.getJavaTypeName(property, jdk5)}"?contains("Int") || "${pojo.getJavaTypeName(property, jdk5)}"?contains("String")
					   	|| "${pojo.getJavaTypeName(property, jdk5)}"?contains("Boolean") || "${pojo.getJavaTypeName(property, jdk5)}"?contains("Date")>
					   		preparedStatement.set${pojo.getJavaTypeName(property, jdk5)}(${count}, pojo.get${pojo.getPropertyName(property)}());
					  	<#else>
					  		<#if "${pojo.getPropertyName(property)}"?contains("By")>
								preparedStatement.setInt(${count}, pojo.getId${"${pojo.getPropertyName(property)}"?substring("${pojo.getPropertyName(property)}"?index_of("By") + 4)}());
							<#else>
					  			preparedStatement.setInt(${count}, pojo.getId${pojo.getJavaTypeName(property, jdk5)}());
				  			</#if>
					    </#if>
					    <#assign count = count + 1>
				    </#if>
			    </#if>
			</#foreach>
			preparedStatement.setInt(${count}, pojo.getId());
		    preparedStatement.executeUpdate();
		    preparedStatement.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
    public int insert(${declarationName} pojo) 
	{
		try 
		{
			<#assign query = "">
			<#assign cols = "">
			<#foreach property in pojo.getAllPropertiesIterator()>
		    <#if "${pojo.getJavaTypeName(property, jdk5)}"?contains("Set")>
		    <#else>
		    <#if "${pojo.getJavaTypeName(property, jdk5)}"?contains("Int") || "${pojo.getJavaTypeName(property, jdk5)}"?contains("String")
				   	|| "${pojo.getJavaTypeName(property, jdk5)}"?contains("Boolean") || "${pojo.getJavaTypeName(property, jdk5)}"?contains("Date")>
				   		<#assign cols = cols + "${property.name},">
				  	<#else>
				  		<#if "${pojo.getPropertyName(property)}"?contains("By")>
							<#assign cols = cols + "id" + "${pojo.getPropertyName(property)}"?substring("${pojo.getPropertyName(property)}"?index_of("B") + 4) +",">
						<#else>
				  			<#assign cols = cols + "id${pojo.getPropertyName(property)},">
			  			</#if>
				    </#if>
		    <#assign query = query + "?,">
		    </#if>
			</#foreach>

			<#assign cols ="${cols}"?substring(0,"${cols}"?length-1)>
			<#assign query ="${query}"?substring(0,"${query}"?length-1)>

			String query = "INSERT INTO " + TABLE + " (${cols}) VALUES (${query});";
			PreparedStatement preparedStatement = DaoConnection.getInstance().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			<#assign count = 1>
			<#foreach property in pojo.getAllPropertiesIterator()>
			    <#if "${pojo.getJavaTypeName(property, jdk5)}"?contains("Set")>
			    <#else>
			    	<#if "${pojo.getJavaTypeName(property, jdk5)}"?contains("Int") || "${pojo.getJavaTypeName(property, jdk5)}"?contains("String")
				   	|| "${pojo.getJavaTypeName(property, jdk5)}"?contains("Boolean") || "${pojo.getJavaTypeName(property, jdk5)}"?contains("Date")>
				   		preparedStatement.set${pojo.getJavaTypeName(property, jdk5)}(${count}, pojo.get${pojo.getPropertyName(property)}());
				  	<#else>
				  		<#if "${pojo.getPropertyName(property)}"?contains("By")>
							preparedStatement.setInt(${count}, pojo.getId${"${pojo.getPropertyName(property)}"?substring("${pojo.getPropertyName(property)}"?index_of("By") + 4)}());
						<#else>
				  			preparedStatement.setInt(${count}, pojo.getId${pojo.getJavaTypeName(property, jdk5)}());
			  			</#if>
				    </#if>
				    <#assign count = count + 1>
			    </#if>
			</#foreach>
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			int id = resultSet.getInt(1);
			preparedStatement.close();
			resultSet.close();

			return id;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return 0;
	}

	@Override
    public List<${declarationName}> getAll() 
	{
		try 
		{
			List<${declarationName}> list = new ArrayList<${declarationName}>();
			String query = "SELECT * FROM " + TABLE;
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) 
			{
				${declarationName} pojo = new ${declarationName}();
				<#foreach property in pojo.getAllPropertiesIterator()>
				    <#if "${pojo.getJavaTypeName(property, jdk5)}"?contains("Set")>
				    <#else>
				    	<#if "${pojo.getJavaTypeName(property, jdk5)}"?contains("Int") || "${pojo.getJavaTypeName(property, jdk5)}"?contains("String")
					   	|| "${pojo.getJavaTypeName(property, jdk5)}"?contains("Boolean") || "${pojo.getJavaTypeName(property, jdk5)}"?contains("Date")>
					   		pojo.set${pojo.getPropertyName(property)}(resultSet.get${pojo.getJavaTypeName(property, jdk5)}("${property.name}"));
					  	<#else>
					  		<#if "${pojo.getPropertyName(property)}"?contains("By")>
								pojo.setId${"${pojo.getPropertyName(property)}"?substring("${pojo.getPropertyName(property)}"?index_of("By") + 4)}(resultSet.getInt("id${"${pojo.getPropertyName(property)}"?substring("${pojo.getPropertyName(property)}"?index_of("By") + 4)}"));
							<#else>
					  			pojo.setId${pojo.getJavaTypeName(property, jdk5)}(resultSet.getInt("id${pojo.getPropertyName(property)}"));
				  			</#if>
					    </#if>
				    </#if>
				</#foreach>
				list.add(pojo);
		    } 
	
			resultSet.close();
			statement.close();
	
			return list;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return null;
	}
    
    @Override
	public ${declarationName} getById(int id${declarationName}) 
	{
		try 
		{
			${declarationName} pojo = new ${declarationName}();
			String query = "SELECT * FROM " + TABLE + " WHERE id = ?";
			PreparedStatement statement = DaoConnection.getInstance().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, id${declarationName});
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) 
			{
				<#foreach property in pojo.getAllPropertiesIterator()>
				    <#if "${pojo.getJavaTypeName(property, jdk5)}"?contains("Set")>
				    <#else>
				    	<#if "${pojo.getJavaTypeName(property, jdk5)}"?contains("Int") || "${pojo.getJavaTypeName(property, jdk5)}"?contains("String")
					   	|| "${pojo.getJavaTypeName(property, jdk5)}"?contains("Boolean") || "${pojo.getJavaTypeName(property, jdk5)}"?contains("Date")>
					   		pojo.set${pojo.getPropertyName(property)}(resultSet.get${pojo.getJavaTypeName(property, jdk5)}("${property.name}"));
					  	<#else>
							<#if "${pojo.getPropertyName(property)}"?contains("By")>
								pojo.setId${"${pojo.getPropertyName(property)}"?substring("${pojo.getPropertyName(property)}"?index_of("By") + 4)}(resultSet.getInt("id${"${pojo.getPropertyName(property)}"?substring("${pojo.getPropertyName(property)}"?index_of("By") + 4)}"));
							<#else>
					  			pojo.setId${pojo.getJavaTypeName(property, jdk5)}(resultSet.getInt("id${pojo.getPropertyName(property)}"));
				  			</#if>					    
				  		</#if>
				    </#if>
				</#foreach>
		    } 
	
			resultSet.close();
			statement.close();
	
			return pojo;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return null;
	}
}
</#assign>

${classbody}
