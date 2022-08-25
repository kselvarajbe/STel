$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/todolist.feature");
formatter.feature({
  "line": 1,
  "name": "To do basic actions on to do list",
  "description": "",
  "id": "to-do-basic-actions-on-to-do-list",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 4,
  "name": "To do list creation",
  "description": "",
  "id": "to-do-basic-actions-on-to-do-list;to-do-list-creation",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 3,
      "name": "@Regression"
    },
    {
      "line": 3,
      "name": "@Sanity"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "I am on Test page",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I create to do list \"\u003ctask1\u003e\" \"\u003ctask2\u003e\"",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "I verfiy all task \"\u003ccount\u003e\" created successfully",
  "keyword": "Then "
});
formatter.examples({
  "line": 9,
  "name": "",
  "description": "",
  "id": "to-do-basic-actions-on-to-do-list;to-do-list-creation;",
  "rows": [
    {
      "cells": [
        "task1",
        "task2",
        "count"
      ],
      "line": 10,
      "id": "to-do-basic-actions-on-to-do-list;to-do-list-creation;;1"
    },
    {
      "cells": [
        "Test1",
        "Test2",
        "2"
      ],
      "line": 11,
      "id": "to-do-basic-actions-on-to-do-list;to-do-list-creation;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 4992112100,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "To do list creation",
  "description": "",
  "id": "to-do-basic-actions-on-to-do-list;to-do-list-creation;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 3,
      "name": "@Sanity"
    },
    {
      "line": 3,
      "name": "@Regression"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "I am on Test page",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I create to do list \"Test1\" \"Test2\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "I verfiy all task \"2\" created successfully",
  "matchedColumns": [
    2
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "HomepageSteps.i_am_on_Test_page()"
});
formatter.result({
  "duration": 507247400,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Test1",
      "offset": 21
    },
    {
      "val": "Test2",
      "offset": 29
    }
  ],
  "location": "HomepageSteps.i_create_to_do_list_something_something(String,String)"
});
formatter.result({
  "duration": 193286800,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "2",
      "offset": 19
    }
  ],
  "location": "HomepageSteps.i_verfiy_all_task_created_successfully(String)"
});
formatter.result({
  "duration": 27532800,
  "status": "passed"
});
formatter.embedding("image/png", "embedded0.png");
formatter.after({
  "duration": 1154596400,
  "status": "passed"
});
formatter.scenarioOutline({
  "line": 16,
  "name": "To do list basic operations",
  "description": "",
  "id": "to-do-basic-actions-on-to-do-list;to-do-list-basic-operations",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 15,
      "name": "@Regression"
    },
    {
      "line": 15,
      "name": "@Sanity"
    }
  ]
});
formatter.step({
  "line": 17,
  "name": "I am on Test page",
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "I create to do list",
  "rows": [
    {
      "cells": [
        "Task1",
        "Test1"
      ],
      "line": 19
    },
    {
      "cells": [
        "Task2",
        "Test2"
      ],
      "line": 20
    },
    {
      "cells": [
        "Task3",
        "Test3"
      ],
      "line": 21
    },
    {
      "cells": [
        "Task4",
        "Test4"
      ],
      "line": 22
    }
  ],
  "keyword": "When "
});
formatter.step({
  "line": 23,
  "name": "I mark as complete a task \"\u003ctaskname\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 24,
  "name": "I click active button",
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "I click all button",
  "keyword": "And "
});
formatter.step({
  "line": 26,
  "name": "I clear completed task",
  "keyword": "And "
});
formatter.step({
  "line": 27,
  "name": "I verfiy that task \"\u003ctaskname\u003e\" cleared successfully",
  "keyword": "Then "
});
formatter.examples({
  "line": 29,
  "name": "",
  "description": "",
  "id": "to-do-basic-actions-on-to-do-list;to-do-list-basic-operations;",
  "rows": [
    {
      "cells": [
        "taskname"
      ],
      "line": 30,
      "id": "to-do-basic-actions-on-to-do-list;to-do-list-basic-operations;;1"
    },
    {
      "cells": [
        "Test2"
      ],
      "line": 31,
      "id": "to-do-basic-actions-on-to-do-list;to-do-list-basic-operations;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 1996440800,
  "status": "passed"
});
formatter.scenario({
  "line": 31,
  "name": "To do list basic operations",
  "description": "",
  "id": "to-do-basic-actions-on-to-do-list;to-do-list-basic-operations;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 15,
      "name": "@Sanity"
    },
    {
      "line": 15,
      "name": "@Regression"
    }
  ]
});
formatter.step({
  "line": 17,
  "name": "I am on Test page",
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "I create to do list",
  "rows": [
    {
      "cells": [
        "Task1",
        "Test1"
      ],
      "line": 19
    },
    {
      "cells": [
        "Task2",
        "Test2"
      ],
      "line": 20
    },
    {
      "cells": [
        "Task3",
        "Test3"
      ],
      "line": 21
    },
    {
      "cells": [
        "Task4",
        "Test4"
      ],
      "line": 22
    }
  ],
  "keyword": "When "
});
formatter.step({
  "line": 23,
  "name": "I mark as complete a task \"Test2\"",
  "matchedColumns": [
    0
  ],
  "keyword": "And "
});
formatter.step({
  "line": 24,
  "name": "I click active button",
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "I click all button",
  "keyword": "And "
});
formatter.step({
  "line": 26,
  "name": "I clear completed task",
  "keyword": "And "
});
formatter.step({
  "line": 27,
  "name": "I verfiy that task \"Test2\" cleared successfully",
  "matchedColumns": [
    0
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "HomepageSteps.i_am_on_Test_page()"
});
formatter.result({
  "duration": 230109400,
  "status": "passed"
});
formatter.match({
  "location": "HomepageSteps.i_create_to_do_list(DataTable)"
});
formatter.result({
  "duration": 346174300,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Test2",
      "offset": 27
    }
  ],
  "location": "HomepageSteps.i_mark_as_complete_a_task_something(String)"
});
formatter.result({
  "duration": 2103044500,
  "status": "passed"
});
formatter.match({
  "location": "HomepageSteps.i_click_active_button()"
});
formatter.result({
  "duration": 2068881700,
  "status": "passed"
});
formatter.match({
  "location": "HomepageSteps.i_click_all_button()"
});
formatter.result({
  "duration": 2082136400,
  "status": "passed"
});
formatter.match({
  "location": "HomepageSteps.i_clear_completed_task()"
});
formatter.result({
  "duration": 22800,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Test2",
      "offset": 20
    }
  ],
  "location": "HomepageSteps.i_verfiy_that_task_cleared_successfully(String)"
});
formatter.result({
  "duration": 94901200,
  "status": "passed"
});
formatter.embedding("image/png", "embedded1.png");
formatter.after({
  "duration": 1094508700,
  "status": "passed"
});
});