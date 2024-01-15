/*
 * Use App.getDependency for Dependency Injection
 * eg: var DialogService = App.getDependency('DialogService');
 */

/* perform any action on widgets/variables within this block */
Page.onReady = function() {
    /*
     * variables can be accessed through 'Page.Variables' property here
     * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
     * Page.Variables.loggedInUser.getData()
     *
     * widgets can be accessed through 'Page.Widgets' property here
     * e.g. to get value of text widget named 'username' use following script
     * 'Page.Widgets.username.datavalue'
     */
};

Page.RmaTable2_customRowAction = function($event, row) {
    Page.Widgets.dialogAssignEngineer.open();
};

Page.getFormattedDate = function(date) {
    return moment(date).format('YYYY-MM-DD');
}

Page.dbGetEngineerSlotsForDateonSuccess = function(variable, data) {
    timeSlots = Page.Variables.dbTimeSlots.dataSet;
    Page.availableSlots = timeSlots.filter(slot => {
        // Check if the slot is not already booked for the selected date and engineer
        const isBooked = data.some(appointment => {
            return appointment.timeSlotId === slot.id
        });
        return !isBooked;
    });

    Page.bookedSlots = timeSlots.filter(slot => !Page.availableSlots.includes(slot));
};
