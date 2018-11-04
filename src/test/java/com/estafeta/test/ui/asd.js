org.testng.internal.reflect.MethodMatcherException
:
Data
provider
mismatch
Method: transport_test([Parameter{
    index = 0,
    type = com.estafeta.test.ui.data.users.UserData,
    declaredAnnotations = []
}, Parameter{index = 1, type = com.estafeta.test.ui.data.users.UserData, declaredAnnotations = []}, Parameter{
    index = 2,
    type = com.estafeta.test.ui.data.admin.transportItems.TransportItemsData,
    declaredAnnotations = []
}, Parameter{
    index = 3,
    type = com.estafeta.test.ui.data.admin.transportItems.TransportItemsData,
    declaredAnnotations = []
}, Parameter{
    index = 4,
    type = com.estafeta.test.ui.data.transports.transports.TransportData,
    declaredAnnotations = []
}])
Arguments: [(com.estafeta.test.ui.data.users.UserData) UserData{
    lastName = 'null',
    firstName = 'null',
    secondName = 'null',
    email = 'null',
    phoneNumbers = [],
    englishLastName = 'null',
    englishFirstName = 'null',
    englishSecondName = 'null',
    language = 'null',
    loginAs = 'estafeta',
    password = '1',
    brands = [],
    company = 'Communication Company Estafeta',
    rolesActive = false,
    userRoles = [],
    systemSettings = null
}, (com.estafeta.test.ui.data.admin.transportItems.TransportItemsData) TransportItemsData{
    active = true,
    verified = true,
    type = 'Car Transporter',
    number = 'XCtMCAgZQA',
    carrier = 'KATP 13061 PJSC',
    trailer = false,
    transportItemPartType = 'null',
    brand = 'DAF',
    model = 'FT75CF',
    description = 'вантажний сідловий тягач',
    vin = 'null'
}, (com.estafeta.test.ui.data.admin.transportItems.TransportItemsData) TransportItemsData{
    active = true,
    verified = true,
    type = 'Car Transporter',
    number = 'bhbShjJRbJaWte',
    carrier = 'KATP 13061 PJSC',
    trailer = true,
    transportItemPartType = 'Eurolohr',
    brand = 'LOHR',
    model = '1.21Е1',
    description = 'Напівпричіп (автовоз)',
    vin = 'null'
}, (com.estafeta.test.ui.data.users.UserData) UserData{
    lastName = 'qFdGIPNzcqvCH',
    firstName = 'nkDmspAoKL',
    secondName = 'gUQhIJVUAeOB',
    email = 'aracely.borer@hotmail.com',
    phoneNumbers = [+380(22)021 - 14 - 96],
    englishLastName = 'HgPLsQDQsjggh',
    englishFirstName = 'pitXXrGxor',
    englishSecondName = 'dFDpNfIeUEBjr',
    language = 'English',
    loginAs = 'pbRjvuZqNxjTF',
    password = 'MKZdQVKGdad',
    brands = [],
    company = 'null',
    rolesActive = true,
    userRoles = [UserRole{
        company = 'KATP 13061 PJSC',
        roleName = [Roles{name = 'Avtologistika Driver'}],
        activityType = 'null'
    }],
    systemSettings = SystemSettings{Verified = true, accessOnlyOwnTasks = true, systemСompany = 'KATP 13061 PJSC'}
},
(com.estafeta.test.ui.data.transports.transports.TransportData)
TransportData
{
    board = 'null', owner = 'Adampol SA', transport = 'XCtMCAgZQA', transportPart = 'bhbShjJRbJaWte', firstDriver = 'HgPLsQDQsjggh'
}
]
