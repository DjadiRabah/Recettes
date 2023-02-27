${pojo.getPackageDeclaration()}
// Generated ${date} by Hibernate Tools ${version}

<#assign date = false>
<#foreach field in pojo.getAllPropertiesIterator()>
	<#if "${pojo.getJavaTypeName(field, jdk5)}"?contains("Date")>
		<#if !date>
			import java.sql.Date;
			<#assign date = true>
		</#if>
	</#if>
</#foreach>

<#assign classbody>
${pojo.getClassModifiers()} ${pojo.getDeclarationType()} ${pojo.getDeclarationName()} implements Pojo
{
<#-- // Fields -->
<#foreach field in pojo.getAllPropertiesIterator()>
    <#if "${pojo.getJavaTypeName(field, jdk5)}"?contains("Set")>
    <#else>
    	<#if "${pojo.getJavaTypeName(field, jdk5)}"?contains("int") || "${pojo.getJavaTypeName(field, jdk5)}"?contains("String")
	   	|| "${pojo.getJavaTypeName(field, jdk5)}"?contains("boolean") || "${pojo.getJavaTypeName(field, jdk5)}"?contains("Date")>
	   		${pojo.getFieldModifiers(field)} ${pojo.getJavaTypeName(field, jdk5)} ${field.name};
	  	<#else>
	  		<#if "${pojo.getJavaTypeName(field, jdk5)}"?contains("Integer")>
	  			${pojo.getFieldModifiers(field)} int ${field.name};
	  		<#else>
	  			<#if "${pojo.getPropertyName(field)}"?contains("By")>
	  				${pojo.getFieldModifiers(field)} int id${"${pojo.getPropertyName(field)}"?substring("${pojo.getPropertyName(field)}"?index_of("By") + 4)};
				<#else>
					${pojo.getFieldModifiers(field)} int id${"${pojo.getPropertyName(field)}"};
	  			</#if>
	  		</#if>
	    </#if>
    </#if>
</#foreach>

	/**
	 * Default empty constructor
	 */
    public ${pojo.getDeclarationName()}() {
    }
    
    <#-- // Property accessors -->
	<#foreach field in pojo.getAllPropertiesIterator()>
		<#if pojo.getMetaAttribAsBool(field, "gen-property", true)>
		 <#if pojo.hasFieldJavaDoc(field)>    
		    /**       
		     * ${pojo.getFieldJavaDoc(field, 4)}
		     */
		</#if>
			<#if "${pojo.getJavaTypeName(field, jdk5)}"?contains("Set")>
			<#else>
				<#if "${pojo.getJavaTypeName(field, jdk5)}"?contains("int") || "${pojo.getJavaTypeName(field, jdk5)}"?contains("String")
	   			|| "${pojo.getJavaTypeName(field, jdk5)}"?contains("boolean") || "${pojo.getJavaTypeName(field, jdk5)}"?contains("Date")>
					${pojo.getPropertyGetModifiers(field)} ${pojo.getJavaTypeName(field, jdk5)} get${pojo.getPropertyName(field)}() {
			        return this.${field.name};
			        }
			    
				    ${pojo.getPropertySetModifiers(field)} void set${pojo.getPropertyName(field)}(${pojo.getJavaTypeName(field, jdk5)} ${field.name}) {
				        this.${field.name} = ${field.name};
				    }
				<#else>
					<#if "${pojo.getJavaTypeName(field, jdk5)}"?contains("Integer")>
					${pojo.getPropertyGetModifiers(field)} int ${pojo.getGetterSignature(field)}() {
			        return this.${field.name};
			        }
			    
				    ${pojo.getPropertySetModifiers(field)} void set${pojo.getPropertyName(field)}(int ${field.name}) {
				        this.${field.name} = ${field.name};
				    }
					<#else>
						<#if "${pojo.getPropertyName(field)}"?contains("By")>
								${pojo.getPropertyGetModifiers(field)} int get${"${pojo.getPropertyName(field)}"?substring("${pojo.getPropertyName(field)}"?index_of("By") + 2)}() {
						        return this.id${"${pojo.getPropertyName(field)}"?substring("${pojo.getPropertyName(field)}"?index_of("By") + 4)};
						        }
						     
							    ${pojo.getPropertySetModifiers(field)} void set${"${pojo.getPropertyName(field)}"?substring("${pojo.getPropertyName(field)}"?index_of("By") + 2)}(int id${"${pojo.getPropertyName(field)}"?substring("${pojo.getPropertyName(field)}"?index_of("By") + 4)}) {
							        this.id${"${pojo.getPropertyName(field)}"?substring("${pojo.getPropertyName(field)}"?index_of("By") + 4)} = id${"${pojo.getPropertyName(field)}"?substring("${pojo.getPropertyName(field)}"?index_of("By") + 4)};
							    }
						<#else>
							${pojo.getPropertyGetModifiers(field)} int getId${"${pojo.getPropertyName(field)}"}() {
						        return this.id${"${pojo.getPropertyName(field)}"};
						        }
						     
							    ${pojo.getPropertySetModifiers(field)} void setId${"${pojo.getPropertyName(field)}"}(int id${"${pojo.getPropertyName(field)}"}) {
							        this.id${"${pojo.getPropertyName(field)}"} = id${"${pojo.getPropertyName(field)}"};
							    }
						</#if>
					</#if>
				</#if>
			</#if>
		</#if>
	</#foreach>
}
</#assign>

${classbody}

