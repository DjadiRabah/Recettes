${pojo.getPackageDeclaration()}
// Generated ${date} by Hibernate Tools ${version}

<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>

import djadi.rabah.recipes.dao.abstractimpl.Abstract${declarationName}DaoImpl;

/**
 * Auto Dao object for domain model class ${declarationName}.
 * @see ${pojo.getQualifiedDeclarationName()}
 * @author Rabah DJADI
 */
public class ${declarationName}Dao extends Abstract${declarationName}DaoImpl{
	private static ${declarationName}Dao dao;
	
	private ${declarationName}Dao()
	{
	}

	public static synchronized ${declarationName}Dao getInstance() {
		if (dao == null)
			dao = new ${declarationName}Dao();
		return dao;
	}
}
</#assign>

${pojo.generateImports()}
${classbody}
