package ua.com.editor;

import java.beans.PropertyEditorSupport;

import ua.com.entity.Author;
import ua.com.service.Author_Service;

public class AuthorEditor extends PropertyEditorSupport {

	private final Author_Service authorservice;

	public AuthorEditor(Author_Service authorservice) {
		super();
		this.authorservice = authorservice;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		Author author = authorservice.findOne(Integer.valueOf(text));

		setValue(author);

	}

}
