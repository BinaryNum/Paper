package com.javalec.paper.command;

import org.springframework.ui.Model;

public interface Command {
	
	void execute(Model model);
	
}