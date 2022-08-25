Feature: To do basic actions on to do list

  @Regression @Sanity
  Scenario Outline: To do list creation
    Given I am on Test page
    When I create to do list "<task1>" "<task2>"
    Then I verfiy all task "<count>" created successfully
    
    Examples:
       | task1| task2|count|
       | Test1| Test2|  2  |
       
       

  @Regression @Sanity
  Scenario Outline: To do list basic operations
    Given I am on Test page
    When I create to do list
       | Task1 |  Test1    |
       | Task2 |  Test2    |
       | Task3 |  Test3    |
       | Task4 |  Test4    |
    And I mark as complete a task "<taskname>"
    And I click active button
    And I click all button
    And I clear completed task
    Then I verfiy that task "<taskname>" cleared successfully
    
    Examples:
       | taskname|
       | Test2   |
          
   
    
      
