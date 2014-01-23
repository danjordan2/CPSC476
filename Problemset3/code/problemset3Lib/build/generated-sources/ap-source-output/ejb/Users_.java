package ejb;

import ejb.Answers;
import ejb.Questions;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-11-19T16:29:11")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, Integer> id;
    public static volatile SingularAttribute<Users, String> username;
    public static volatile CollectionAttribute<Users, Answers> answersCollection;
    public static volatile CollectionAttribute<Users, Questions> questionsCollection;
    public static volatile SingularAttribute<Users, String> password;

}