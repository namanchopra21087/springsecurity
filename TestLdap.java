package com.amtrust.crmz.ldap.authentication.jwt.token.config;

import java.util.Properties;
        import javax.naming.Context;
        import javax.naming.NamingEnumeration;
        import javax.naming.NamingException;
        import javax.naming.directory.Attributes;
        import javax.naming.directory.DirContext;
        import javax.naming.directory.InitialDirContext;
        import javax.naming.directory.SearchControls;
        import javax.naming.directory.SearchResult;

public class TestLdap {



    public void totalUser() throws NamingException
    {

        Properties initialProperties = new Properties();
        initialProperties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        initialProperties.put(Context.PROVIDER_URL, "ldap://ec2-52-77-238-93.ap-southeast-1.compute.amazonaws.com:389");
        initialProperties.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=amtrustmobilesolutions,dc=asia");
        initialProperties.put(Context.SECURITY_CREDENTIALS, "admin");
        DirContext  context = new InitialDirContext(initialProperties);
        String searchFilter="(uid=jtee)";
        SearchControls controls=new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration users=context.search("dc=amtrustmobilesolutions,dc=asia", searchFilter, controls);
        SearchResult searchResult=null;
        String commonName=null;
        String roleOccupant=null;
        while(users.hasMore())
        {
            searchResult=(SearchResult) users.next();
            //System.out.println(searchResult.getName());
            Attributes attr=searchResult.getAttributes();
            //System.out.println(attr);
            /*commonName=attr.get("cn").get(0).toString();
            roleOccupant=attr.get("gidNumber").get(0).toString();
            System.out.println("Name = "+commonName);*/
            System.out.println("gidNumber  = "+attr.get("gidNumber").get(0).toString());
            System.out.println("-------------------------------------------");

        }

    }
    public static void main(String[] args) throws NamingException
    {
        TestLdap sample = new TestLdap();
        sample.totalUser();

    }

}