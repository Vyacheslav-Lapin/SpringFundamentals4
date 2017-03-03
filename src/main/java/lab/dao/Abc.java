package lab.dao;

public class Abc {

//    private TreeMap<Object, Account> accounts;
//    private Component validator;
//
//    @RequestMapping(value = "/availability", method = RequestMethod.GET)
//    @ResponseBody
//    public AvailabilityStatus getAvailability(@RequestParam String name) {
//        for (Account a : accounts.values()) {
//            if (a.getName().equals(name)) {
//                return AvailabilityStatus.notAvailable(name);
//            }
//        }
//
//        return AvailabilityStatus.available();
//    }
//
//    @RequestMapping(method=RequestMethod.POST)
//    @ResponseBody
//    public Map<String, ?> create(
//            @RequestBody Account account,
//            HttpServletResponse response) {
////        Set<ConstraintViolation<Account>> failures = validator.validate(account);
////        if (!failures.isEmpty()) {
////            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
////            return validationMessages(failures);
////        } else {
////            accounts.put(account.assignId(), account);
////            return Collections.singletonMap("id", account.getId());
//        return null;
//        }
//    }
//
//    class AvailabilityStatus {
//        public static AvailabilityStatus notAvailable(String name) {
//            return null;
//        }
//
//        public static AvailabilityStatus available() {
//            return null;
//        }
//    }
//
//    class Account {
//        private Object name;
//
//        public Object getName() {
//            return name;
//        }
//
//        public void setName(Object name) {
//            this.name = name;
//        }
//    }
}
