#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;

import ${package}.testdata.CommentTestdataBuilder;
import de.akquinet.jbosscc.needle.junit.DatabaseRule;

public class CommentTest {
	@Rule
	public DatabaseRule databaseRule = new DatabaseRule();

	private EntityManager entityManager = databaseRule.getEntityManager();

	@Test
	public void testPersist() throws Exception {
		Comment comment = new CommentTestdataBuilder(entityManager).buildAndSave();

		Comment commentFromDb = databaseRule.getEntityManager().find(Comment.class,
				comment.getId());

		Assert.assertEquals(comment.getId(), commentFromDb.getId());
		Assert.assertNotSame(comment, commentFromDb);
	}
}
