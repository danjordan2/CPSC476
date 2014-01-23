package ejb;

import ejb.Answers;
import ejb.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-11-19T16:29:11")
@StaticMetamodel(Questions.class)
public class Questions_ { 

    public static volatile SingularAttribute<Questions, Integer> id;
    public static volatile SingularAttribute<Questions, String> text;
    public static volatile SingularAttribute<Questions, Date> createdAt;
    public static volatile SingularAttribute<Questions, Users> userId;
    public static volatile CollectionAttribute<Questions, Answers> answersCollection;

}