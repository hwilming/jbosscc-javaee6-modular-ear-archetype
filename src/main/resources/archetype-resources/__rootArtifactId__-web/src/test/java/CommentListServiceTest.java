#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Rule;
import org.junit.Test;

import ${package}.dao.CommentDao;
import ${package}.testdata.BlogEntryTestdataBuilder;
import ${package}.testdata.CommentTestdataBuilder;
import de.akquinet.jbosscc.needle.annotation.ObjectUnderTest;
import de.akquinet.jbosscc.needle.junit.NeedleRule;
import de.akquinet.jbosscc.needle.mock.EasyMockProvider;

public class CommentListServiceTest {

	@Rule
	public NeedleRule needleRule = new NeedleRule();

	@ObjectUnderTest
	private CommentListService commentListService;

	@Inject
	private EasyMockProvider mockProvider;

	@Inject
	private BlogEntryService blogEntryService;

	@Inject
	private CommentDao commentDao;

	// TODO: This test case is not really useful, because it is only a mock protocol
	// test, but it shows the needle features ;-).
	@Test
	public void testGetResultList() throws Exception {
		BlogEntry blogEntry = new BlogEntryTestdataBuilder().build();
		EasyMock.expect(blogEntryService.getInstance()).andReturn(blogEntry);

		EasyMock.expect(commentDao.findComments(blogEntry)).andReturn(Arrays.asList(new CommentTestdataBuilder().build()));

		mockProvider.replayAll();
		List<Comment> resultList = commentListService.getResultList();

		Assert.assertEquals(1, resultList.size());

		mockProvider.verifyAll();

	}
}
