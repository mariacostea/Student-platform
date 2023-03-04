public abstract class User {
        private String firstName, lastName;
        public User(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
        public String toString() {
            return firstName + " " + lastName;
        }

        public String get_firstname(){
            return this.firstName;
        }

        public String get_lastname(){
            return this.lastName;
        }
    }


