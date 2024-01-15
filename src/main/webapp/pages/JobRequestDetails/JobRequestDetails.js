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

Page.RmaLiveForm1_resetAction = function($event) {
    Page.Widgets.dialogCreateRMA.close();
};

Page.RmaLiveForm1Beforeservicecall = function($event, $operation, $data, options) {
    $data.rmaNumber = generateRMAId();
    let randomIndex = Math.floor(Math.random() * Page.Variables.dbPaymentTypes.dataSet.length);
    const paymentTypeId = Page.Variables.dbPaymentTypes.dataSet[randomIndex].id;
    $data.paymentTypeId = paymentTypeId;
    $data.quotationAmount = 500;
    $data.isRmaCreated = true;
    $data.createdOn = new Date();
};

function generateRMAId() {
    return 'INW' + Math.floor(Math.random() * 10000000000);
}
