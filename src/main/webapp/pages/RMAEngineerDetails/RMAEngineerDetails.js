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
    Page.Variables.stvParts.dataSet = [];
};

Page.buttonAddPartToListClick = function($event, widget) {
    let partSale = Page.Widgets.PartSaleLiveForm1.dataoutput;
    partSale.price = ((Page.Widgets.PartSaleLiveForm1.formWidgets.part.datavalue.price * (100 - Page.Widgets.PartSaleLiveForm1.formWidgets.discount.datavalue) / 100) * Page.Widgets.PartSaleLiveForm1.formWidgets.quantity.datavalue);
    Page.Variables.stvParts.dataSet.push(partSale);
    Page.Widgets.dialogAddPart.close();
};

Page.stvPartsTable1_deleterowAction = function($event, row) {
    Page.Variables.stvParts.dataSet.splice(row.$index - 1, 1);
};

Page.getPartsTotal = function(array) {
    let sum = array.reduce((total, item) => total + item.price, 0);
    return sum;
}

Page.buttonCloseTicketClick = function($event, widget) {
    let repairActivity = Page.Widgets.RepairActivityLiveForm1.dataoutput;
    repairActivity.partSales = Page.Variables.stvParts.dataSet;

    Page.Variables.svCreateRepairActivity.setInput("RepairActivity", repairActivity);
    Page.Variables.svCreateRepairActivity.invoke();
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

Page.PartSaleTable1Beforedatarender = function(widget, data, columns) {
    columns["part.name"].setSummaryRowData([
        'Parts Total'
    ]);

    const columnAggregate = columns.price.aggregate;
    columns.price.setSummaryRowData('₹' + columnAggregate.sum());
};

Page.anchorCheckInClick = function($event, widget) {
    Page.Widgets.RepairActivityLiveForm1.formWidgets.date.datavalue = new moment().format('YYYY-MM-DD');
    Page.Widgets.RepairActivityLiveForm1.formWidgets.startTime.datavalue = new moment();
    Page.Widgets.anchorCheckIn.show = false;
    Page.Widgets.anchorCheckOut.show = true;
};

Page.anchorCheckOutClick = function($event, widget) {
    Page.Widgets.RepairActivityLiveForm1.formWidgets.endTime.datavalue = new moment();
    Page.Widgets.anchorCheckOut.show = false;
};
