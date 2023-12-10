/**
 * This is a test suite for testing drag and drop, resize, scroll, and click actions.
 */
describe('My Test', () => {
    /**
     * This test performs drag and drop, resize, scroll, and click actions.
     */
    it('performs drag and drop, resize, scroll, and click', () => {
        
      /**
       * Drag & Drop:
       * The draggable element is selected using the 'a[href="/abtest"]' CSS selector.
       * The droppable element is selected using the 'a[href="/add_remove_elements/"]' CSS selector.
       */
      cy.get("a[href='/abtest']").trigger('mousedown', { which: 1 });
      cy.get("a[href='/add_remove_elements/']").trigger('mousemove').trigger('mouseup', { force: true });
  
      /**
       * Scroll to element:
       * The element is selected using the 'a[href="/basic_auth"]' CSS selector.
       */
      cy.get("a[href='/basic_auth']").scrollIntoView();
  
      /**
       * Verify element is in view:
       * The element is selected using the 'a[href="/checkboxes"]' CSS selector.
       */
      cy.get("a[href='/checkboxes']").should('be.visible');
  
      /**
       * Click using JS:
       * The element is selected using the 'a[href="/login"]' CSS selector.
       */
      cy.get("a[href='/login']").click();
    });
});
