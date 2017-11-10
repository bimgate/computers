Feature: CRUD operations

  Background:
    Given server API is up and running on "http://computer-database.herokuapp.com/computers"
    And   response code is 200
    When user navigate to "http://computer-database.herokuapp.com/computers"
    And get data about Total sum of created computers in Heading text

  Scenario Outline: Landing page
    Then landing page appears with elements: "<Title>", "<Subtitle>", "<Search field>", "<Search button>", "<Add button>", "<Tab column I>", "<Tab column II>", "<Tab column III>", "<Tab column IV>"
    And close browser
    Examples:
    | Title             | Subtitle                                     | Search field | Search button  | Add button         | Tab column I | Tab column II | Tab column III | Tab column IV |
    | Computers database | Play sample application — Computer database |  input  |  Filter by name | Add a new computer | Computer name | Introduced   | Discontinued   | Company      |

  Scenario Outline: Add new computer
    When user click on button "Add a new computer"
    And new page appears with elements: "<Heading text>", "<Form field I>", "<Form field II>", "<Form field III>", "<Form field IV>", "<Button I>" , "<Button II>"
    And populate Computer name with name info "Amiga3000"
    And populate introduced with date info "1995-02-15"
    And populate discontinued date with date info "2000-04-17"
    And select company from drop-down list "Amiga Corporation"
    And Click on button Create this computer
    Then landing page appears with message "Done! Computer Amiga3000 has been created"
    And Total sum of created computers in Heading text is greater for one
    And close browser
    Examples:
    | Heading text | Form field I | Form field II | Form field III | Form field IV | Button I | Button II |
    | Add a computer | name | introduced | discontinued | company | Create this computer | http://computer-database.herokuapp.com/computers |

  Scenario Outline: Add new computer - Cancel
    When user click on button "Add new computer"
    And new page appears with elements: "<Heading text>", "<Form field I>", "<Form field II>", "<Form field III>", "<Form field IV>", "<Button I>" , "<Button II>"
    And populate Computer name with name info "Amiga3000"
    And populate introduced with date info "1995-02-15"
    And populate discontinued date with date info "2000-04-17"
    And select company from drop-down list "Amiga Corporation"
    And Click on button Cancel
    Then landing page appears
    And Total sum of created computers in Heading text is unchanged.
    And close browser
    Examples:
      | Heading text | Form field I | Form field II | Form field III | Form field IV | Button I | Button II |
      | Add a computer | name | introduced | discontinued | company | Create this computer | http://computer-database.herokuapp.com/computers |

  Scenario Outline: Add new computer - Empty required field
    When user click on button "Add new computer"
    And new page appears with elements: "<Heading text>", "<Form field I>", "<Form field II>", "<Form field III>", "<Form field IV>", "<Button I>" , "<Button II>"
    And populate introduced with date info "1995-02-15"
    And populate discontinued date with date info "2000-04-17"
    And select company from drop-down list "Amiga Corporation"
    And Click on button Create this computer
    Then alert appears for required field Computer name
    And close browser
    Examples:
      | Heading text | Form field I | Form field II | Form field III | Form field IV | Button I | Button II |
      | Add a computer | name | introduced | discontinued | company | Create this computer | http://computer-database.herokuapp.com/computers |

  Scenario Outline: Add new computer - Wrong date format
    When user click on button "Add new computer"
    And new page appears with elements: "<Heading text>", "<Form field I>", "<Form field II>", "<Form field III>", "<Form field IV>", "<Button I>" , "<Button II>"
    And populate Computer name with name info "Amiga3000"
    And populate introduced with date info "02-15-1995"
    And populate discontinued date with date info "04-17-2000"
    And select company from drop-down list "Amiga Corporation"
    And Click on button Create this computer
    Then alert appears for fields "<Form field II>", "<Form field III>"
    And close browser
    Examples:
      | Heading text | Form field I | Form field II | Form field III | Form field IV | Button I | Button II |
      | Add a computer | name | introduced | discontinued | company | Create this computer | http://computer-database.herokuapp.com/computers |

  Scenario Outline: Edit computer
    When Populate Search field with "Amiga3000"
    And Click on button Filter by name
    And only computer with requested name is shown "Amiga3000"
    When click on Computer name "Amiga3000"
    And new page appears with elements: "<Heading text>", "<Form field I>", "<Form field II>", "<Form field III>", "<Form field IV>", "<Button I>" , "<Button II>", "<Button III>"
    And populate Computer name with name info "Commodore64A"
    And populate introduced with date info "1993-02-15"
    And populate discontinued date with date info "1999-04-17"
    And select company from drop-down list "Commodore International"
    And Click on button Save this computer
    Then landing page appears with message "Done! Computer Commodore64A has been updated"
    And Total sum of created computers in Heading text is unchanged.
    And close browser
    Examples:
      | Heading text | Form field I | Form field II | Form field III | Form field IV | Button I | Button II | Button III |
      | Edit computer | name | introduced | discontinued | company | Save this computer | http://computer-database.herokuapp.com/computers | Delete this computer |

  Scenario: Delete computer
    When Populate Search field with "Commodore64A"
    And Click on button Filter by name
    And only computer with requested name is shown "Commodore64A"
    When click on Computer name "Commodore64A"
    And new page appears with title "Edit computer"
    And Click on button Delete this computer
    Then landing page appears with message "Done! Computer has been deleted"
    And Total sum of created computers in Heading text is less for one
    And close browser
