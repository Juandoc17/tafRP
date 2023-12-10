Cypress.Commands.add('dragAndDrop', (subject, target) => {
    cy.get(subject).trigger('mousedown', { which: 1 });
    cy.get(target).trigger('mousemove').trigger('mouseup', { force: true });
  });
  